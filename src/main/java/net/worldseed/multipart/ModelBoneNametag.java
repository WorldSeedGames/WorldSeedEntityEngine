package net.worldseed.multipart;

import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.instance.Instance;
import net.minestom.server.tag.Tag;

non-sealed class ModelBoneNametag extends ModelBoneGeneric {
    public ModelBoneNametag(Point pivot, String name, Point rotation, GenericModel model, LivingEntity nametagEntity) {
        super(pivot, name, rotation, model);

        if (this.offset != null) {
            this.stand = nametagEntity;
            this.stand.setTag(Tag.String("WSEE"), "nametag");
        }
    }

    @Override
    public void setState(String state) { }

    public void draw(short tick) {
        if (this.offset == null) return;

        Point p = this.offset;
        p = applyTransform(p, tick);
        p = applyGlobalRotation(p);

        double divisor = 1;
        if (model.getRenderType() == ModelEngine.RenderType.SMALL_ARMOUR_STAND || model.getRenderType() == ModelEngine.RenderType.SMALL_ZOMBIE) {
            divisor = 1.6;
        }

        Pos endPos = Pos.fromPoint(p)
            .div(6.4, 6.4, 6.4)
            .div(divisor)
            .add(model.getPosition())
            .add(model.getGlobalOffset());

        stand.teleport(endPos);
    }

    @Override
    public void spawn(Instance instance, Point position) {
    }
}

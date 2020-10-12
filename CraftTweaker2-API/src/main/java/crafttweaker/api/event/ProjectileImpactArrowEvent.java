package crafttweaker.api.event;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.entity.IEntity;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenSetter;

@ZenClass("crafttweaker.event.ProjectileImpactArrowEvent")
@ZenRegister
public interface ProjectileImpactArrowEvent extends IProjectileImpactEvent, IEventCancelable {
    @ZenGetter("arrow")
    IEntity getArrow();

    @ZenGetter("shooter")
    IEntity getShooter();

    @ZenGetter("damage")
    double getDamage();

    @ZenSetter("damage")
    void setDamage(double damage);

    @ZenSetter("knockbackStrength")
    void setKnockbackStrength(int knockbackStrength);

    @ZenGetter("isCritical")
    boolean getIsCritical();

    @ZenSetter("isCritical")
    void setIsCritical(boolean isCritical);

    @ZenGetter("pickupStatus")
    String getPickupStatus();

    @ZenMethod
    void setPickupDisallowed();

    @ZenMethod
    void setPickupAllowed();

    @ZenMethod
    void setPickupCreativeOnly();
}

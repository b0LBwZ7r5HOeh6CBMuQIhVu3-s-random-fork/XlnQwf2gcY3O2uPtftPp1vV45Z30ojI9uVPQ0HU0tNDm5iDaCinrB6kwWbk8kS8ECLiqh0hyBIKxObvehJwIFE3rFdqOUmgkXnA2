/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.render;

import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.module.modules.client.Rotations
import net.ccbluex.liquidbounce.utils.RotationUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Random;

@Mixin(ModelBiped.class)
@SideOnly(Side.CLIENT)
public class MixinModelBiped {

    @Shadow
    public ModelRenderer bipedRightArm;
    @Shadow
    public ModelRenderer bipedLeftArm;
    @Shadow
    public int heldItemRight;

    @Shadow
    public ModelRenderer bipedHead;

    @Inject(method = "setRotationAngles", at = @At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelBiped;swingProgress:F"))
    private void revertSwordAnimation(float p_setRotationAngles_1_, float p_setRotationAngles_2_, float p_setRotationAngles_3_, float p_setRotationAngles_4_, float p_setRotationAngles_5_, float p_setRotationAngles_6_, Entity p_setRotationAngles_7_, CallbackInfo callbackInfo) {
        if (heldItemRight == 3)
            this.bipedRightArm.rotateAngleY = 0F;

        if (Rotations.INSTANCE.getHeadValue().get() && RotationUtils.serverRotation != null && p_setRotationAngles_7_ instanceof EntityPlayer
                && p_setRotationAngles_7_.equals(Minecraft.getMinecraft().thePlayer)) {
            this.bipedHead.rotateAngleX = RotationUtils.serverRotation.getPitch() / (180F / (float) Math.PI);
        // return;
        }
         if (!p_setRotationAngles_7_.equals(Minecraft.getMinecraft().thePlayer)) {
            // this.bipedHead.rotateAngleX = 0.5F;
            /* 182 */
            // this.bipedHead.rotateAngleY = 0.75F;
            /* 183 */
            this.bipedRightArm.rotateAngleX = (-5 + ((5 - -5) * Math.random()));
            /* 184 */
            this.bipedRightArm.rotateAngleY = (-5 + ((5 - -5) * Math.random()));
            /* 185 */
            this.bipedLeftArm.rotateAngleX = (-5 + ((5 - -5) * Math.random()));
            /* 186 */
            this.bipedLeftArm.rotateAngleY = (-5 + ((5 - -5) * Math.random()));
         }
    }
}
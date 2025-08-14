package io.github.bigcrazyofficial.showtime.item;

import io.github.bigcrazyofficial.showtime.entity.ShowtimeBallEntity;
import io.github.bigcrazyofficial.showtime.item.component.ComponentTypes;
import io.github.bigcrazyofficial.showtime.util.Sounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ShowtimeStaffItem extends Item {
    public int RECHARGE_TIMER = 20;
    public ShowtimeStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return MathHelper.hsvToRgb(1.0f, 0.5f, 1.0f);
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        float i = (float)stack.get(ComponentTypes.BALL_CHARGES);
        return MathHelper.clamp(Math.round(i * 13.0f / 4f), 0, 13);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot){
        int charges = stack.get(ComponentTypes.BALL_CHARGES);
        if(charges <= 3){
            RECHARGE_TIMER--;
            if(RECHARGE_TIMER <= 0){
                RECHARGE_TIMER = 20;
                stack.set(ComponentTypes.BALL_CHARGES, charges + 1);
                world.playSound(null, entity.getBlockPos(), Sounds.STAFF_RECHARGE, SoundCategory.PLAYERS, 1, 1);
            }
        }
    }
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        int charges = stack.get(ComponentTypes.BALL_CHARGES);
        if (charges > 0) {
            if (world instanceof ServerWorld serverWorld) {
                ShowtimeBallEntity ball = new ShowtimeBallEntity(serverWorld, user, user.getX(), user.getEyeY(), user.getZ());
                ball.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.1f, 0f);
                world.spawnEntity(ball);
            }
            world.playSound(null, user.getBlockPos(), Sounds.BALL_THROW, SoundCategory.PLAYERS, 1, 1);
            stack.set(ComponentTypes.BALL_CHARGES, charges - 1);
            RECHARGE_TIMER = 30;
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}

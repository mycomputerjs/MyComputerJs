package net.youritch.pythonputer.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.GrindstoneEvent;
import net.youritch.pythonputer.PythonputerMod;
import org.jetbrains.annotations.NotNull;
import org.python.util.PythonInterpreter;

import javax.annotation.Nullable;

public class Computer extends Block {
    private PythonInterpreter pythonInterp;

    public Computer(Properties properties) {
        super(properties);
    }
    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        try {
            this.pythonInterp = new PythonInterpreter();
        } catch (Error e) {
            PythonputerMod.LOGGER.error("Error during creation of an pythonInterpreter :\n"+e);
            return;
        }
        PythonputerMod.LOGGER.info("Successfully launch the python interpreter !");
    }
    @Override
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        level.playSound(player, pos, SoundEvents.REDSTONE_TORCH_BURNOUT, SoundSource.BLOCKS,
                1f, 1f);
        this.pythonInterp.exec("print('Hello Python World!')");
        return InteractionResult.SUCCESS;
    }
}
package net.youritch.pythonputer.block.custom;

import jep.Interpreter;
import jep.MainInterpreter;
import jep.SharedInterpreter;
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
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.youritch.pythonputer.PythonputerMod;

import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class Computer extends Block {
    private Interpreter pythonInterp;
    private boolean interpState = false;

    public Computer(Properties properties) {
        super(properties);
        ArrayList<String> path = new ArrayList<String>(Arrays.stream(System.getProperty("user.dir").split("/")).toList());
        path.remove(path.size()-1);
        path.remove(path.size()-1);
        PythonputerMod.LOGGER.info("> Jep Libs path : "+String.join("/",path)+"/jep/libjep.jnilib");
        //MainInterpreter.setJepLibraryPath("/Users/youri/Documents/GitHub/MyComputerJs/jep/libjep.jnilib");
        MainInterpreter.setJepLibraryPath(String.join("/",path)+"/jep/libjep.jnilib");
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        if(pLevel.isClientSide) return;
        PythonputerMod.LOGGER.info("> Trying to launch the python interpreter !");
        try {
            this.pythonInterp = new SharedInterpreter();
        } catch (Error e) {
            PythonputerMod.LOGGER.error("> Error during creation of an pythonInterpreter :\n"+e);
            return;
        }
        this.interpState=true;
        PythonputerMod.LOGGER.info("> Successfully launch the python interpreter !");
        if (this.interpState) {
            PythonputerMod.LOGGER.info("> Trying to test the python interpreter !");
            try {
                //this.pythonInterp.eval("print('Hello Python World!')");
                this.pythonInterp.eval("x=3");
                this.pythonInterp.eval("x=x**2");
                Object result1 = this.pythonInterp.getValue("x");
                PythonputerMod.LOGGER.info("> Get resp from py :"+result1);
            }catch (Error e) {
                PythonputerMod.LOGGER.error("> Error during using an pythonInterpreter :\n"+e);
            }
        }
    }
    @Override
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        level.playSound(player, pos, SoundEvents.REDSTONE_TORCH_BURNOUT, SoundSource.BLOCKS,
                1f, 1f);
        if(level.isClientSide) return InteractionResult.SUCCESS;
        if (this.interpState) {
            PythonputerMod.LOGGER.info("> Trying to test the python interpreter !");
            try {
                //this.pythonInterp.eval("print('Hello Python World!')");
                this.pythonInterp.eval("x=x**2");
                Object result1 = this.pythonInterp.getValue("x");
                PythonputerMod.LOGGER.info("> Get resp from py :"+result1);
            }catch (Error e) {
                PythonputerMod.LOGGER.error("> Error during using an pythonInterpreter :\n"+e);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (this.interpState && !level.isClientSide) {
            PythonputerMod.LOGGER.info("> Trying to close the python interpreter !");
            try {
                this.pythonInterp.close();
            }catch (Error e) {
                PythonputerMod.LOGGER.error("> Error during closing an pythonInterpreter :\n"+e);
            }
        }
        PythonputerMod.LOGGER.info("> Successfully close the python interpreter !");
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
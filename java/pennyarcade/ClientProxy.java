package pennyarcade;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import pennyarcade.block.render.RenderClawMachine;
import pennyarcade.block.render.RenderEmeraldPusher;
import pennyarcade.block.render.RenderMiniCreeper;
import pennyarcade.block.render.RenderMiniEnderman;
import pennyarcade.block.render.RenderMiniPig;
import pennyarcade.block.render.RenderPennyPusher1;
import pennyarcade.block.render.RenderPennyPusher2;
import pennyarcade.block.render.RenderRubixCube;
import pennyarcade.block.render.item.ItemRenderer;
import pennyarcade.block.render.item.ItemRendererMiniCreeper;
import pennyarcade.block.render.item.ItemRendererMiniEnderman;
import pennyarcade.block.render.item.ItemRendererMiniPig;
import pennyarcade.block.render.item.ItemRendererPennyPusher1;
import pennyarcade.block.render.item.ItemRendererPennyPusher2;
import pennyarcade.block.render.item.ItemRendererRubixCube;
import pennyarcade.block.render.model.ModelClawMachine;
import pennyarcade.block.tileentity.TileEntityClawMachine;
import pennyarcade.block.tileentity.TileEntityEmeraldPusher;
import pennyarcade.block.tileentity.TileEntityMiniCreeper;
import pennyarcade.block.tileentity.TileEntityMiniEnderman;
import pennyarcade.block.tileentity.TileEntityMiniPig;
import pennyarcade.block.tileentity.TileEntityPennyPusher1;
import pennyarcade.block.tileentity.TileEntityPennyPusher2;
import pennyarcade.block.tileentity.TileEntityRubixCube;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	public void registerRenderers() {

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PennyArcade.pennyPusher1), new ItemRendererPennyPusher1(new RenderPennyPusher1(), new TileEntityPennyPusher1()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PennyArcade.pennyPusher2), new ItemRendererPennyPusher2(new RenderPennyPusher2(), new TileEntityPennyPusher2()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PennyArcade.pennyPusher3), new ItemRendererPennyPusher1(new RenderEmeraldPusher(), new TileEntityEmeraldPusher()));
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PennyArcade.clawMachine), new ItemRenderer(new RenderClawMachine(), new TileEntityClawMachine(), new ModelClawMachine()));

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PennyArcade.miniCreeper), new ItemRendererMiniCreeper(new RenderMiniCreeper(), new TileEntityMiniCreeper()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PennyArcade.miniPig), new ItemRendererMiniPig(new RenderMiniPig(), new TileEntityMiniPig()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PennyArcade.miniEnderman), new ItemRendererMiniEnderman(new RenderMiniEnderman(), new TileEntityMiniEnderman()));

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PennyArcade.rubixCube), new ItemRendererRubixCube(new RenderRubixCube(), new TileEntityRubixCube()));
		/////////

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPennyPusher1.class, new RenderPennyPusher1());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPennyPusher2.class, new RenderPennyPusher2());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEmeraldPusher.class, new RenderEmeraldPusher());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityClawMachine.class, new RenderClawMachine());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMiniCreeper.class, new RenderMiniCreeper());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMiniPig.class, new RenderMiniPig());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMiniEnderman.class, new RenderMiniEnderman());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRubixCube.class, new RenderRubixCube());
	}
}

package pennyarcade;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.config.Configuration;
import pennyarcade.block.*;
import pennyarcade.block.player.BlockHerobrine;
import pennyarcade.block.tileentity.*;
import pennyarcade.block.tileentity.player.*;
import pennyarcade.entity.villager.VillagerTradeHandler;
import pennyarcade.event.AchievementManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

@Mod(modid = PennyArcade.MODID, name = "Penny Arcade Mod", version = PennyArcade.VERSION)
public class PennyArcade {

	public static final String MODID = "pennyarcade";
	public static final String VERSION = "Alpha 1.2";

	@Instance(MODID)
	public static PennyArcade instance;
	@SidedProxy(clientSide = "pennyarcade.ClientProxy", serverSide = "pennyarcade.CommonProxy")
	public static CommonProxy proxyArcade;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Block pennyPusher1;
	public static Block pennyPusher2;
	public static Block pennyPusher3;
	public static Block outOfOrderMachine;
	
	public static Block clawMachine;
	public static Block pinballMachine;
	public static Block fruitMachine;

	public static Block miniCreeper;
	public static Block miniPig;
	public static Block miniEnderman;
	
	public static Block rubixCube;
	public static Block nyanCat;
	public static Block stephano;
	
	public static Block playerHerobrine;

	public static Item goldCoin;
	public static Item emeraldToken;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static int coinChance;
	public static int maxCoinAmount;
	public static int prizeChance;
	
	public static int clawMachineChance;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static CreativeTabs pennyArcadeTab;
	
	public static Achievement[] achievements;
	
	public static Achievement achievementGoldCoins;
	public static Achievement achievementPennyPusher;
	public static Achievement achievementEmeraldToken;
	public static Achievement achievementEmeraldPusher;
	public static Achievement achievementNyanCat;
	public static Achievement achievementClawMachine;
	public static Achievement achievementStephano;
	public static Achievement achievementHerobrine;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		pennyArcadeTab = new CreativeTabs("pennyArcadeTab") { public Item getTabIconItem() { return goldCoin; }};

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

		coinChance = config.get(Configuration.CATEGORY_GENERAL, "coinChance", 30, "The chance out of 100 that you win coins. Default of 30").getInt();
		maxCoinAmount = config.get(Configuration.CATEGORY_GENERAL, "maxCoinAmount", 4, "The maximum amount of coins that you win coins. Default of 4").getInt();
		prizeChance = config.get(Configuration.CATEGORY_GENERAL, "prizeChance", 5, "The chance out of 100 that you win a prize. Default of 5").getInt();
		clawMachineChance = config.get(Configuration.CATEGORY_GENERAL, "clawMachineChance", 5, "The chance out of 100 that you win a prize from a claw machine. Default of 5").getInt();

		config.save();
		
		pennyPusher1 = new BlockPennyPusher1().setBlockName("pennyPusher1").setCreativeTab(pennyArcadeTab);
		pennyPusher2 = new BlockPennyPusher2().setBlockName("pennyPusher2").setCreativeTab(pennyArcadeTab);
		pennyPusher3 = new BlockEmeraldPusher().setBlockName("pennyPusher3").setCreativeTab(pennyArcadeTab);
		outOfOrderMachine = new BlockOutOfOrderMachine().setBlockName("outOfOrder").setCreativeTab(pennyArcadeTab);
		
		clawMachine = new BlockClawMachine().setBlockName("clawMachine").setCreativeTab(pennyArcadeTab);
		pinballMachine = new BlockPinball().setBlockName("pinballMachine").setCreativeTab(pennyArcadeTab);
		fruitMachine = new BlockFruitMachine().setBlockName("fruitMachine").setCreativeTab(pennyArcadeTab);

		miniCreeper = new BlockMiniCreeper().setBlockName("miniCreeper").setCreativeTab(pennyArcadeTab);
		miniPig = new BlockMiniPig().setBlockName("miniPig").setCreativeTab(pennyArcadeTab);
		miniEnderman = new BlockMiniEnderman().setBlockName("miniEnderman").setCreativeTab(pennyArcadeTab);
		
		rubixCube = new BlockRubixCube().setBlockName("rubixCube").setCreativeTab(pennyArcadeTab);
		nyanCat = new BlockNyanCat().setBlockName("nyanCat").setCreativeTab(pennyArcadeTab);
		stephano = new BlockStephano().setBlockName("stephano").setCreativeTab(pennyArcadeTab);
		
		playerHerobrine = new BlockHerobrine().setBlockName("herobrine").setCreativeTab(pennyArcadeTab);
		
		goldCoin = new Item().setUnlocalizedName("goldCoin").setTextureName(PennyArcade.MODID + ":goldCoin").setCreativeTab(pennyArcadeTab);
		emeraldToken = new Item().setUnlocalizedName("emeraldToken").setTextureName(PennyArcade.MODID + ":emeraldToken").setCreativeTab(pennyArcadeTab);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		achievementGoldCoins = new Achievement("achievement.goldcoin", "goldcoin", 0, 0, this.goldCoin, AchievementList.buildBetterPickaxe).registerStat();
		achievementPennyPusher = new Achievement("achievement.pennypusher", "pennypusher", 2, 1, Items.glowstone_dust, this.achievementGoldCoins).registerStat();
		achievementClawMachine = new Achievement("achievement.clawmachine", "clawmachine", 2, -1, Items.iron_ingot, this.achievementGoldCoins).registerStat();
		achievementEmeraldToken = new Achievement("achievement.emeraldtoken", "emeraldtoken", 4, 1, this.emeraldToken, this.achievementPennyPusher).registerStat();
		achievementEmeraldPusher = new Achievement("achievement.emeraldpusher", "emeraldpusher", 6, 2, Items.emerald, this.achievementEmeraldToken).registerStat();
		achievementNyanCat = new Achievement("achievement.nyancat", "nyancat", 2, -3, Items.cookie, this.achievementClawMachine).setSpecial().registerStat();
		achievementStephano = new Achievement("achievement.stephano", "stephano", 3, -2,  Items.gold_ingot, this.achievementClawMachine).setSpecial().registerStat();
		achievementHerobrine = new Achievement("achievement.herobrine", "herobrine", 1, -2,  Items.ghast_tear, this.achievementClawMachine).setSpecial().registerStat();
		
		achievements = new Achievement[] { this.achievementGoldCoins, this.achievementPennyPusher, this.achievementEmeraldToken, this.achievementEmeraldPusher, this.achievementNyanCat, this.achievementClawMachine, this.achievementStephano, this.achievementHerobrine};
		
		AchievementPage.registerAchievementPage(new AchievementPage("Penny Arcade Mod", this.achievements));
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		registerObjects();
		addCraftingRecipes();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		proxyArcade.registerRenderers();

		registerEntities();
		
		FMLCommonHandler.instance().bus().register(new AchievementManager());
	}

	public void addCraftingRecipes() {
		
		ItemStack lapiz = new ItemStack(Items.dye, 1, 4);
		
		GameRegistry.addRecipe(new ItemStack(goldCoin, 1), new Object[] { "XX", Character.valueOf('X'), Items.gold_nugget });

		GameRegistry.addRecipe(new ItemStack(pennyPusher1, 1), new Object[] { "III", "IPI", "IEI", Character.valueOf('I'), Items.iron_ingot, Character.valueOf('P'), Blocks.glass_pane, Character.valueOf('E'), Items.emerald });
		GameRegistry.addRecipe(new ItemStack(pennyPusher2, 1), new Object[] { "IPI", "IGI", "III", Character.valueOf('I'), Items.redstone, Character.valueOf('P'), Blocks.glass_pane, Character.valueOf('G'), Items.gold_ingot });
		GameRegistry.addRecipe(new ItemStack(pennyPusher3, 1), new Object[] { "IEI", "EPE", "IEI", Character.valueOf('I'), Items.iron_ingot, Character.valueOf('P'), Blocks.glass_pane, Character.valueOf('E'), Items.emerald });
		
		GameRegistry.addRecipe(new ItemStack(clawMachine, 1), new Object[] { "IEI", "PPP", "IOI", Character.valueOf('I'), Items.redstone, Character.valueOf('P'), Blocks.glass_pane, Character.valueOf('E'), Items.glowstone_dust, Character.valueOf('O'), Blocks.obsidian });
		GameRegistry.addRecipe(new ItemStack(pinballMachine, 1), new Object[] { "RGR", "IPI", "I I", Character.valueOf('R'), Items.redstone, Character.valueOf('P'), Blocks.glass_pane, Character.valueOf('I'), Items.iron_ingot, Character.valueOf('G'), Items.glowstone_dust });
		GameRegistry.addRecipe(new ItemStack(fruitMachine, 1), new Object[] { "BFB", "BDB", "BRB", Character.valueOf('B'), lapiz, Character.valueOf('R'), Items.redstone, Character.valueOf('D'), Items.diamond, Character.valueOf('F'), Items.melon });
	}


	public void registerEntities() {
		
		GameRegistry.registerTileEntity(TileEntityPennyPusher1.class, "pennyPusher1");
		GameRegistry.registerTileEntity(TileEntityPennyPusher2.class, "pennyPusher2");
		GameRegistry.registerTileEntity(TileEntityEmeraldPusher.class, "pennyPusher3");
		GameRegistry.registerTileEntity(TileEntityOutOfOrderMachine.class, "outOfOrder");
		
		GameRegistry.registerTileEntity(TileEntityClawMachine.class, "clawMachine");
		GameRegistry.registerTileEntity(TileEntityPinball.class, "pinballMachine");
		GameRegistry.registerTileEntity(TileEntityFruitMachine.class, "fruitMachine");

		GameRegistry.registerTileEntity(TileEntityMiniCreeper.class, "miniCreeper");
		GameRegistry.registerTileEntity(TileEntityMiniPig.class, "miniPig");
		GameRegistry.registerTileEntity(TileEntityMiniEnderman.class, "miniEnderman");
		
		GameRegistry.registerTileEntity(TileEntityRubixCube.class, "rubixCube");
		GameRegistry.registerTileEntity(TileEntityNyanCat.class, "nyanCat");
		GameRegistry.registerTileEntity(TileEntityStephano.class, "stephano");
		
		GameRegistry.registerTileEntity(TileEntityHerobrine.class, "herobrine");
		 
		registerVillagers();
	}

	public void registerObjects() {
		
		GameRegistry.registerBlock(pennyPusher1, "pennyPusher1");
		GameRegistry.registerBlock(pennyPusher2, "pennyPusher2");
		GameRegistry.registerBlock(pennyPusher3, "pennyPusher3");
		GameRegistry.registerBlock(outOfOrderMachine, "outOfOrder");
		
		GameRegistry.registerBlock(clawMachine, "clawMachine");
		GameRegistry.registerBlock(pinballMachine, "pinballMachine");
		GameRegistry.registerBlock(fruitMachine, "fruitMachine");

		GameRegistry.registerBlock(miniCreeper, "miniCreeper");
		GameRegistry.registerBlock(miniPig, "miniPig");
		GameRegistry.registerBlock(miniEnderman, "miniEnderman");
	
		GameRegistry.registerBlock(rubixCube, "rubixCube");
		GameRegistry.registerBlock(nyanCat, "nyanCat");
		GameRegistry.registerBlock(stephano, "stephano");
		
		GameRegistry.registerBlock(playerHerobrine, "herobrine");
		
		GameRegistry.registerItem(goldCoin, "goldCoin");
		GameRegistry.registerItem(emeraldToken, "emeraldToken");
		
	}

	public void registerVillagers() {
		
		VillagerRegistry.instance().registerVillagerId(10);
		VillagerRegistry.instance().registerVillagerSkin(10, new ResourceLocation(PennyArcade.MODID + ":textures/entity/villager/CoinMerchant.png"));
		
		VillagerTradeHandler coinMerchant = new VillagerTradeHandler();
		VillagerRegistry.instance().registerVillageTradeHandler(10, coinMerchant);
	}

}

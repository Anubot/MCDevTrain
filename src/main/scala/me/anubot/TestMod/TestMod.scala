package me.anubot.TestMod

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import me.anubot.TestMod.core.{Configurator, TestModProxy}

/** Minecraft Forge test mod to learn scala and forge api
 *
 * It is made merely for practice and serves no actual purpose. However, I hope to record my findings and
 * create a wiki to share them with people who may need it.
 *
 * @author Anubot
 * @version @VERSION@
 */
@Mod(modid = Configurator.modID, name = Configurator.modName, version = Configurator.version,
  acceptedMinecraftVersions = "[1.7.10]", dependencies = "after:*",  modLanguage = "scala")
object TestMod {

  /** Initializes configs and performs pre-init operations
   *
   * @param e Passed event parameter from mod loader
   */
  @EventHandler
  def preInit(e: FMLPreInitializationEvent): Unit = {
    TestModProxy.preInit()
  }

  /** Performs init operations
   *
   * @param e Passed event parameter from mod loader
   */
  @EventHandler
  def init(e: FMLInitializationEvent): Unit = {
    TestModProxy.init()
  }

  /** Performs post-init operations
   *
   * @param e Passed event parameter from mod loader
   */
  @EventHandler
  def postInit(e: FMLPostInitializationEvent): Unit = {
    TestModProxy.postInit()
  }
}

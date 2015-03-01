package me.anubot.TestMod

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import me.anubot.TestMod.core.{Configurator, TestModProxy}

/**
 * This is a Forge Gradle Mod template
 *
 * @author Anubot
 * @version @VERSION@
 */
@Mod(modid = Configurator.modID, name = Configurator.modName, version = Configurator.version, acceptedMinecraftVersions = "[1.7.10]", dependencies = "after:*",  modLanguage = "scala")
object TestMod {

  @EventHandler
  def preInit(e: FMLPreInitializationEvent): Unit = {
    TestModProxy.preInit()
  }

  @EventHandler
  def init(e: FMLInitializationEvent): Unit = {
    TestModProxy.init()
  }

  @EventHandler
  def postInit(e: FMLPostInitializationEvent): Unit = {
    TestModProxy.postInit()
  }
}

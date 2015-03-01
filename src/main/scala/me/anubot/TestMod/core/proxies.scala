package me.anubot.TestMod.core

import cpw.mods.fml.relauncher.{Side, SideOnly}
import me.anubot.TestMod.LOGGER

/** Defines the minimal proxy implementation. Should mirror the Mod class. */
sealed trait TProxy {
  def preInit(): Unit
  def init(): Unit
  def postInit(): Unit
}

/** The server component of the mod loading process. */
sealed class TestModProxy_common extends TProxy {
  override def preInit(): Unit = {
    LOGGER.info("Server Pre-Init")
  }

  override def init(): Unit = {
    LOGGER.info("Server Init")
  }

  override def postInit(): Unit = {
    LOGGER.info("Server Post-Init")
  }
}

/** The client component of the mod loading process */
sealed class TestModProxy_client extends TestModProxy_common {
  @SideOnly(Side.CLIENT)
  override def init(): Unit = {
    super.init()
    LOGGER.info("Client Init")
  }

  @SideOnly(Side.CLIENT)
  override def postInit(): Unit = {
    super.postInit()
    LOGGER.info("Client Post-Init")
  }
}

/** The singleton instance of the mod proxy used to initialize */
object TestModProxy extends TestModProxy_client {}
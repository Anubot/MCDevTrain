package me.anubot.TestMod.core

import cpw.mods.fml.relauncher.{Side, SideOnly}
import me.anubot.TestMod.LOGGER


sealed trait TProxy {
  def preInit(): Unit
  def init(): Unit
  def postInit(): Unit
}

sealed class TestModProxy_serverImpl extends TProxy {
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

sealed class TestModProxy_clientImpl extends TestModProxy_serverImpl {
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

object TestModProxy extends TestModProxy_clientImpl {}
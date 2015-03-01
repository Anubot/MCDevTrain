package me.anubot

import me.anubot.TestMod.core.Configurator
import org.apache.logging.log4j.{LogManager, Logger}

package object TestMod {
  lazy val LOGGER: Logger = LogManager.getLogger(Configurator.modID)
}

package me.anubot.TestMod.core

import java.io.File
import java.util.UUID

import cpw.mods.fml.client.event.ConfigChangedEvent
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import me.anubot.TestMod.LOGGER
import net.minecraftforge.common.UsernameCache

import scala.collection.immutable.TreeMap

object Configurator {
  final val modID: String = "@MODID@"
  final val modName: String = "@MODNAME@"
  final val version: String = "@VERSION@"
  final val globalConfigVer: String = "@CONFIG_GLOBAL@"
  final val worldConfigVer: String = "@CONFIG_WORLD@"
  final val playerConfigVer: String = "@CONFIG_PLAYER@"
  final val buildNumber: String = "@BUILD_NUMBER@"
  val global: ConfigBase = new ConfigBase
  val world: ConfigBase = new ConfigBase
  var players: Map[UUID, ConfigBase] = new TreeMap[UUID, ConfigBase]

  def initGlobalConfig(): Unit = {
    val configFile = new File("configs/" + Configurator.modID + ".cfg")
    ConfigBase.initConfig(ConfigBase.Type.GLOBAL, global, configFile)
    LOGGER.info("Initializing Global Configs.")
  }

  def initWorldConfig(save: String): Unit = {
    val configFile = new File("saves/" + save + "/" + Configurator.modID + "/world.cfg")
    ConfigBase.initConfig(ConfigBase.Type.WORLD, world, configFile)
    LOGGER.info("Initializing World Configs.")
  }

  def initPlayerConfig(save: String, uid: UUID): Unit = {
    val configFile = new File("saves/" + save + "/" + Configurator.modID + "/players/" + uid.toString + ".cfg")
    val player = new ConfigBase
    ConfigBase.initConfig(ConfigBase.Type.PLAYER, player, configFile)
    players += uid -> player
    LOGGER.info("Initializing " + UsernameCache.getLastKnownUsername(uid) + " Configs.")
  }
}

class Configurator {
  @SubscribeEvent
  def onConfigChangedEvent(event: ConfigChangedEvent.OnConfigChangedEvent): Unit = {
    if(Configurator.modID.equals(event.modID)) {

      val t: String = event.configID

      import me.anubot.TestMod.core.ConfigBase.Type._
      t match {
        case GLOBAL.toString => Configurator.global.syncConfig;
        case WORLD.toString => Configurator.world.syncConfig;
        case _ => {
          val uid: UUID =
            try {
              UUID.fromString(t)
            } catch {
              case e: IllegalArgumentException => {
                LOGGER.fatal("Invalid UUID.", e)
                System.exit(-1)
                null
              }
            }
          Configurator.players.apply(uid).syncConfig
        }
      }
    }
  }
}

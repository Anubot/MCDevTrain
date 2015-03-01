package me.anubot.TestMod.core

import java.io.File
import java.util.{ArrayList => JAList, List => JList}

import cpw.mods.fml.client.config.DummyConfigElement.DummyCategoryElement
import cpw.mods.fml.client.config.IConfigElement
import net.minecraftforge.common.config.{ConfigElement, Configuration}

class ConfigBase(private var forgeConfig: Configuration = null, private var config: IConfigElement[_]= null) {
  def syncConfig(load: Boolean): Unit = {
    this.forgeConfig.load()
  }

  def syncConfig(): Unit = {
    syncConfig(false)
  }

  def getChildrenElements(): JList[IConfigElement[_]] = {
    config.getChildElements
  }
}

object ConfigBase {
  object Type extends Enumeration {
    type Type = Value
    val GLOBAL, WORLD, PLAYER = Value
  }

  import me.anubot.TestMod.core.ConfigBase.Type._
  def initConfig(t: Type, base: ConfigBase, configFile: File): Unit = {
    val children = new JAList[IConfigElement[_]]
    var langKeyBase = Configurator.modID + ".config."

    t match {
      case GLOBAL => {
        langKeyBase += GLOBAL.toString
        base.forgeConfig = new Configuration(configFile, Configurator.globalConfigVer)
        children.add(new ConfigElement(base.forgeConfig.get("", "test_property", "test", "Global Test Property").setLanguageKey(langKeyBase + ".prop.test_property")))

        base.config = new DummyCategoryElement(GLOBAL.toString.toLowerCase, langKeyBase, children)
      }
      case WORLD => {
        langKeyBase += WORLD.toString
        base.forgeConfig = new Configuration(configFile, Configurator.worldConfigVer)
        children.add(new ConfigElement(base.forgeConfig.get("", "test_property", "test", "World Test Property").setLanguageKey(langKeyBase + ".prop.test_property")))

        base.config = new DummyCategoryElement(WORLD.toString.toLowerCase, langKeyBase, children)
      }
      case PLAYER => {
        langKeyBase += PLAYER.toString
        base.forgeConfig = new Configuration(configFile, Configurator.playerConfigVer)
        children.add(new ConfigElement(base.forgeConfig.get("", "test_property", "test", "Player Test Property").setLanguageKey(langKeyBase + ".prop.test_property")))

        base.config = new DummyCategoryElement(PLAYER.toString.toLowerCase, langKeyBase, children)
      }
    }
  }
}


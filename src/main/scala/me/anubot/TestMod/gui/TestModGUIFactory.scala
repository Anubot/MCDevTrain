package me.anubot.TestMod.gui

import java.util.{List => JList, Set => JSet}

import cpw.mods.fml.client.IModGuiFactory
import cpw.mods.fml.client.IModGuiFactory.{RuntimeOptionCategoryElement, RuntimeOptionGuiHandler}
import cpw.mods.fml.client.config.{GuiConfig, IConfigElement}
import me.anubot.TestMod.core.{ConfigBase, Configurator}
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.resources.I18n

/** This class is responsible for creating the different config GUI screens */
class TestModGuiFactory extends IModGuiFactory {
  override def initialize(minecraftInstance: Minecraft): Unit = {}
  override def runtimeGuiCategories(): JSet[RuntimeOptionCategoryElement] = null
  override def getHandlerFor(element: RuntimeOptionCategoryElement): RuntimeOptionGuiHandler = null
  override def mainConfigGuiClass(): Class[_ <: GuiScreen] = classOf[TestModConfigGuiGlobal]

  import ConfigBase.Type._
  class TestModConfigGuiGlobal(parentScreen: GuiScreen) extends GuiConfig(parentScreen, TestModGuiFactory.TestModConfigGuiGlobal.getConfigElements(), Configurator.modID, GLOBAL.toString, false, false, I18n.format(Configurator.modID + ".config.global.title" )) {}
}

/** Static elements of the TestModGuiFactory */
object TestModGuiFactory {
  object TestModConfigGuiGlobal {
    def getConfigElements(): JList[IConfigElement[_]] = {
      Configurator.global.config.getChildElements
    }
  }
}
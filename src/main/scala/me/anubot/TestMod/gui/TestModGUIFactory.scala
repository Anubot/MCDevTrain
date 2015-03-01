package me.anubot.TestMod.gui

import java.util.{List => JList, Set => JSet}

import cpw.mods.fml.client.IModGuiFactory
import cpw.mods.fml.client.IModGuiFactory.{RuntimeOptionCategoryElement, RuntimeOptionGuiHandler}
import cpw.mods.fml.client.config.{GuiConfig, IConfigElement}
import me.anubot.TestMod.core.{ConfigBase, Configurator}
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.resources.I18n

object TestModGuiFactory {

  object TestModConfigGui {
    def getConfigElements(): JList[IConfigElement[_]] = {
      Configurator.global.getChildrenElements()
    }
  }
}

class TestModGuiFactory extends IModGuiFactory {
  override def initialize(minecraftInstance: Minecraft): Unit = {}
  override def runtimeGuiCategories(): JSet[RuntimeOptionCategoryElement] = null
  override def getHandlerFor(element: RuntimeOptionCategoryElement): RuntimeOptionGuiHandler = null
  override def mainConfigGuiClass(): Class[_ <: GuiScreen] = classOf[TestModConfigGui]

  import me.anubot.TestMod.core.ConfigBase.Type._
  class TestModConfigGui(parentScreen: GuiScreen) extends GuiConfig(parentScreen, TestModGuiFactory.TestModConfigGui.getConfigElements(), Configurator.modID, GLOBAL.toString, false, false, I18n.format(Configurator.modID + ".config." + GLOBAL.toString + ".title" )) {}
}
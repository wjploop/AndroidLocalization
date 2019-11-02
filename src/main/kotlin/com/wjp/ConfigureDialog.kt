package com.wjp

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import java.awt.BorderLayout
import java.awt.Container
import java.awt.GridLayout
import java.awt.event.ItemEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*

class ConfigureDialog(val project: Project?) : DialogWrapper(project, true) {


    val selectedLang = mutableListOf<Language>()

    init {
        title = "Configure and Convert"
        init()
    }


    override fun createCenterPanel(): JComponent? {
        val panel = JPanel(BorderLayout(16, 6))
        val container = Container()
        val properties = PropertiesComponent.getInstance(project)
        val token = properties.getValue("key_baidu_token", "")
        val toLangsString = properties.getValue("key_to_languages", "")

        val supportLangs = Language.values()

        val toLangs = toLangsString.split(", ").map {
            fromCode(it)
        }


        container.layout = GridLayout(supportLangs.size / 4, 4)
        supportLangs.forEach { lang ->
            container.add(JCheckBox(lang.englishName).apply {
                addItemListener {
                    if (it.stateChange == ItemEvent.SELECTED) {
                        selectedLang.add(lang)
                    } else {
                        selectedLang.remove(lang)
                    }
                }
                if (toLangs.isNotEmpty() && toLangs.contains(lang)) {
                    isSelected = true
                }
            })
        }
        panel.add(container, BorderLayout.CENTER)
        val tokenInput=Container().apply {
            layout=GridLayout(2,2)
            add(JLabel("Token of Baidu Translate:"))
            add(JTextField())
            val baiduStr="http:www.baidu.com"
            add(JLabel("<html><a href='$baiduStr'>baidu</a></html>").apply {
                addMouseListener(object:MouseAdapter(){
                    override fun mouseClicked(e: MouseEvent?) {
                        try{
                            Runtime.getRuntime().exec("cmd.exe /c start $baiduStr")
                        }catch (e:Exception){
                            e.printStackTrace()
                        }
                    }
                })
            })
        }
        panel.add(tokenInput,BorderLayout.NORTH)

        return panel
    }

    override fun doOKAction() {
        super.doOKAction()
        if (selectedLang.isEmpty()) {
            Messages.showErrorDialog("Please select the language you want to convert to", "Error")
        }
        val selectedLanguagesString = selectedLang.map { it.codeForApi }.joinToString()
        PropertiesComponent.getInstance(project).setValue("key_to_languages", selectedLanguagesString)

    }

}


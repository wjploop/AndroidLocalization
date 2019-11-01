package com.wjp

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.awt.BorderLayout
import javax.swing.JComponent
import javax.swing.JPanel

class ConfigureDialog(val project:Project):DialogWrapper(project,true){




    init {
        title="Configure and Convert"
        init()
    }



    override fun createCenterPanel(): JComponent? {
        val panel=JPanel(BorderLayout(16,6))
        val properties=PropertiesComponent.getInstance(project)
        val token = properties.getValue("baidu_token", "")
        val toLangsString=properties.getValue("to_langs","")

        val supportLangs=Language.values()

        val toLangs=toLangsString.split(",").forEach {
            Language.valueOf()
        }
        return panel
    }

}


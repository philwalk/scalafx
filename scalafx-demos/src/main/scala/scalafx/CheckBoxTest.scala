/*
 * Copyright (c) 2011-2023, ScalaFX Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the ScalaFX Project nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE SCALAFX PROJECT OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package scalafx

import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.*
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color

object CheckBoxTest extends JFXApp3 {
  override def start(): Unit = {
    val check         = new CheckBox { text = "CheckBox" }
    val lblCheckState = new Label { text = check.selected.get().toString }
    check.onAction = () => {
      lblCheckState.text = if (check.indeterminate.get) "Indeterminate" else check.selected.get().toString
    }
    val btnAllowIndeterminate = new scalafx.scene.control.Button { text = "Allow Indeterminate" }
    btnAllowIndeterminate.onAction = () => {
      check.allowIndeterminate = !check.allowIndeterminate.get()
    }
    val lblAllowIndeterminate = new Label {
      text <== when(check.allowIndeterminate) choose "Can be Indeterminate" otherwise "Can not be Indeterminate"
    }
    val btnFire = new Button { text = "Fire!" }
    btnFire.onAction = () => check.fire()
    val txfText = new TextField
    txfText.delegate.textProperty.bindBidirectional(check.text)
    val grid = new GridPane(7, 7) {
      padding = Insets(14)
    }
    grid.add(check, 0, 0)
    grid.add(lblCheckState, 1, 0)
    grid.add(btnAllowIndeterminate, 0, 1)
    grid.add(lblAllowIndeterminate, 1, 1)
    grid.add(btnFire, 0, 2)
    grid.add(txfText, 1, 2)
    stage = new PrimaryStage {
      title = "CheckBox Test"
      scene = new Scene {
        fill = Color.LightGray
        content = grid
      }
    }
  }
}

import scalafx.application.JFXApp3
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.layout.VBox

import java.io.File

object main extends JFXApp3 {
  
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "Mp3 merger"
      resizable = false
      scene = new Scene {
        root = new VBox {
          children = layout.mainVBoxElements
          alignment = Pos.Center
          alignmentInParent = Pos.Center
        }
      }
    }
  }
  
}

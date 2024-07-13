import buttonEvents.{addButton, clearButton, downButton, mergeButton, rmCurButton, upButton}
import javafx.collections.ListChangeListener
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Node
import scalafx.scene.control.{Button, CheckBox, Label, ListView}
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.text.Font

object layout {

  private val data: dataObj = dataObj()

  /**
   * @return
   * list of elements for the main vbox
   */
  def mainVBoxElements : Seq[Node] = Seq(
    mkLabel("Mp3 audio merger"),
    mkButton("Merge audio files", () => mergeButton(data)),
    mkCheckBox,
    mkHBox)

  private def mkButton(text : String, f : () => Unit) : Button = new Button(text){
      margin = Insets(5)
      prefHeight = 50
      onMouseClicked = _ => f()
    }

  private def mkLabel(text : String) : Label = new Label(text){
    style = s"-fx-background-color: transparent;\n\n-fx-font-weight: bold;\n"
    font = new Font("Sakkal Majalla",18)
    margin = Insets(5)
  }

  private def mkCheckBox : CheckBox = new CheckBox("Sort alphabetically"){
    this.selected = true
    margin = Insets(5)
    this.selected.bindBidirectional(data.getSorted)
    this.alignment = Pos.Center
  }

  private def mkList : ListView[String] = new ListView[String](){
    this.getSelectionModel.setSelectionMode(javafx.scene.control.SelectionMode.SINGLE)
    margin = Insets(5)
    this.setPrefWidth(1000)
    this.items.bindBidirectional(data.getMp3List)
    this.getSelectionModel.getSelectedIndices.addListener(new ListChangeListener[Integer] {
      override def onChanged(change: ListChangeListener.Change[_ <: Integer]): Unit = {
        if change.getList.size() > 0 then data.setIndex(change.getList.get(0))
        else if data.getIndex.value > 0 then data.setIndex(data.getIndex.value - 1)
        else if data.getMp3List.value.size() > 0 then data.setIndex(0)
        else data.setIndex(-1)
      }
    })
  }

  private def mkHBox : HBox = new HBox{
    margin = Insets(5)
    children = Seq(
      mkList,
      mkVBox
    )
  }

  private def mkVBox : VBox = new VBox{
    margin = Insets(5)
    alignment = Pos.Center
    alignmentInParent = Pos.Center
    children = Seq(
      mkButton("Add", () => addButton(data)),
      mkButton("Remove Selected", () => rmCurButton(data)),
      mkButton("Clear All", () => clearButton(data)),
      mkButton("Move Selected Up", () => upButton(data)),
      mkButton("Move Selected Down", () => downButton(data))
    )
  }
}

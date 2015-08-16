package protobufui.gui.workspace

import javafx.scene.control.ContextMenu

import ipetoolkit.util.Message
import ipetoolkit.workspace.WorkspaceEntry

import scala.xml.Elem

/**
 * Created by krever on 8/14/15.
 */
class TestsEntry extends WorkspaceEntry {
  override def uid: String = "testsWorkspaceEntry"

  override def toXml: Option[Elem] = ???

  override def contextMenu: Option[ContextMenu] = None

  override def detailsOpener: Option[Message] = None

  override def toString: String = "Tests"

}

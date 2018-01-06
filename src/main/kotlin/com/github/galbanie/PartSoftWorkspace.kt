package com.github.galbanie

import com.github.galbanie.views.*
import javafx.geometry.Insets
import tornadofx.*

/**
 * Created by Galbanie on 2017-10-09.
 */
class PartSoftWorkspace : Workspace() {
    init {
        add(TopMenuView::class)
        add(UserMenuView::class)
        add(SearchBarView::class)
    }

    init {
        with(leftDrawer){
            item(ProjectListView::class){
                padding = Insets(10.0)
            }
            item(CatalogListView::class){
                padding = Insets(10.0)
            }
        }
        with(rightDrawer){
            item(DataSourceListView::class){
                padding = Insets(10.0)
            }
        }
        with(bottomDrawer){

        }
    }

    init {
        with(root){
            prefHeight = 768.0
            prefWidth = 1024.0
        }
    }
}
package com.github.galbanie.views

import com.github.galbanie.models.DataSource
import tornadofx.*

/**
 * Created by Galbanie on 18-01-01.
 */
class DataSourceListView : View("Data Sources") {
    override val root = listview<DataSource> {

    }
}

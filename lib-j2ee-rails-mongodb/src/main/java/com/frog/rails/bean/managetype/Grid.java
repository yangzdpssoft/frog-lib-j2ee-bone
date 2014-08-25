package com.frog.rails.bean.managetype;

import com.frog.rails.bean.managetype.inline.Button;
import com.frog.rails.bean.managetype.inline.GridColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * 网格.
 * @author yangz
 * @date 2014/8/25
 *
 */
public class Grid {
    /**
     * 按钮组.
     */
    private List<Button> buttonList = new ArrayList<>();
    /**
     * 网格列.
     */
    private List<GridColumn> gridColumnList = new ArrayList<>();

    public List<GridColumn> getGridColumnList() {
        return gridColumnList;
    }

    public void setGridColumnList(List<GridColumn> gridColumnList) {
        this.gridColumnList = gridColumnList;
    }

    public List<Button> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<Button> buttonList) {
        this.buttonList = buttonList;
    }
}

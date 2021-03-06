/*
 * Copyright (C) 2013 Manuel Peinado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.manuelpeinado.multichoiceadapter.samples.actionbarcompat.customactionmodetitlesample;

import java.util.ArrayList;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.manuelpeinado.multichoiceadapter.extras.actionbarcompat.MultiChoiceArrayAdapter;
import com.manuelpeinado.multichoiceadapter.samples.actionbarcompat.R;

@SuppressLint("ViewConstructor")
public class CustomActionModeTitleAdapter extends MultiChoiceArrayAdapter<String> {

    protected static final String TAG = CustomActionModeTitleAdapter.class.getSimpleName();

    public CustomActionModeTitleAdapter(Bundle savedInstanceState, Context context, ArrayList<String> items) {
        super(savedInstanceState, context, R.layout.mca__simple_list_item_checkable_1, android.R.id.text1, items);
    }
    
    @Override
    public String getActionModeTitle(int selected) {
    	return String.format("%d / %d", selected, getCount());
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.my_action_mode, menu);
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        if (item.getItemId() == R.id.menu_share) {
            Toast.makeText(getContext(), "Share", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId() == R.id.menu_discard) {
            discardSelectedItems();
            return true;
        }
        return false;
    }

    private void discardSelectedItems() {
        Set<Long> selection = getCheckedItems();
        String[] items = new String[selection.size()];
        int i = 0;
        for (long position : selection) {
            items[i++] = getItem((int)position);
        }
        for (String item : items) {
            remove(item);
        }
        finishActionMode();
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }
    
    @Override
    protected View getViewImpl(int position, View convertView, ViewGroup parent) {
        View view = super.getViewImpl(position, convertView, parent);
        view.setBackgroundResource(R.drawable.custom_list_item_background);
        return view;
    }
}

/*
 * Copyright (c) 2016, The Linux Foundation. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above
 *    copyright notice, this list of conditions and the following
 *    disclaimer in the documentation and/or other materials provided
 *    with the distribution.
 *  * Neither the name of The Linux Foundation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
 * IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.android.gallery3d.filtershow.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.codeaurora.gallery.R;
import com.android.gallery3d.util.GalleryUtils;

public class DoNotShowAgainDialog extends DialogFragment {
    private int mSharedPrefKeyId;
    private int mTitleId;
    private int mMessageId;
    private CheckBox mDoNotShowAgainChk;

    public DoNotShowAgainDialog(int titleId, int msgId, int sharedPrefKeyId) {
        mTitleId = titleId;
        mMessageId = msgId;
        mSharedPrefKeyId = sharedPrefKeyId;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.do_not_show_again_dialog, null);
        TextView message = (TextView) view.findViewById(R.id.message);
        message.setText(mMessageId);
        mDoNotShowAgainChk = (CheckBox) view.findViewById(R.id.do_not_show_chk);

        AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
        ab.setTitle(mTitleId);
        ab.setView(view);
        ab.setCancelable(false);
        ab.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Context context = getActivity();
                GalleryUtils.setBooleanPref(context,
                        context.getString(mSharedPrefKeyId), mDoNotShowAgainChk.isChecked());
            }
        });
        return ab.create();
    }
}
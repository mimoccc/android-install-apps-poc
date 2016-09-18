package me.tombailey.installappspoc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tom on 18/09/2016.
 */

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    private OnFileSelected mOnFileSelected;
    private File[] mFiles;

    public FileAdapter(OnFileSelected onFileSelected) {
        this(onFileSelected, null);
    }

    public FileAdapter(OnFileSelected onFileSelected, File[] files) {
        mOnFileSelected = onFileSelected;
        setFiles(files);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.file_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setFile(mFiles[position]);
    }

    @Override
    public int getItemCount() {
        return mFiles == null ? 0 : mFiles.length;
    }

    public void setFiles(File[] files) {
        mFiles = files;
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.file_item_view_image_view_icon)
        ImageView mFileIcon;
        @BindView(R.id.file_item_view_text_view_name)
        TextView mFileName;

        private File mFile;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setFile(File file) {
            mFile = file;
            mFileIcon.setImageResource(file.isDirectory() ?
                    R.drawable.ic_folder_black_24dp : R.drawable.ic_insert_drive_file_black_24dp);
            mFileName.setText(file.getName());
        }

        @OnClick(R.id.file_item_view_text_view_name)
        public void fileSelected() {
            mOnFileSelected.onFileSelected(mFile);
        }

    }

    public interface OnFileSelected {
        void onFileSelected(File file);
    }
}

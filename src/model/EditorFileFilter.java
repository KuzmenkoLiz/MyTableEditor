package model;

import javax.swing.filechooser.FileFilter;

/**
 * Created by Лиза on 12.05.2017.
 */
public class EditorFileFilter extends FileFilter {
    private String ext;

    public EditorFileFilter(String ext) {
        this.ext = ext;
    }

    @Override
    public boolean accept(java.io.File file) {
        if (file.isDirectory()) return true;
        return (file.getName().endsWith(ext));
    }

    @Override
    public String getDescription() {
        return "*" + ext;
    }
}

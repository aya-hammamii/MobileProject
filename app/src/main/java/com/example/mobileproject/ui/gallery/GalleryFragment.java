package com.example.mobileproject.ui.gallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileproject.R;
import com.example.mobileproject.databinding.FragmentGalleryBinding;

import java.io.File;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;


    public static final int IMAGE_GALLERY_REQUEST=20;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        ImageView imageView = binding.imageGallery;
        imageView=root.findViewById(R.id.image_gallery);

        Button btn = binding.buttonGallery;
        btn=root.findViewById(R.id.button_gallery);


        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                i.setAction(Intent.ACTION_VIEW);
//                startActivity(i);

                Intent photoPickerIntent=new Intent(Intent.ACTION_PICK);
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureDirectoryPath = pictureDirectory.getPath();

                Uri data= Uri.parse(pictureDirectoryPath);
                photoPickerIntent.setDataAndType(data, "image/*");
                startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
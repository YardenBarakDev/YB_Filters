# YB_Filters

[![](https://jitpack.io/v/YardenBarakDev/YB_Filters.svg)](https://jitpack.io/#YardenBarakDev/YB_Filters)

A library for a simple implementation of Filters and color manipulation. the user will be able to set an Image using Glide(https://github.com/bumptech/glide) and add filters to the image.
this library also support an easy way to request camera permission 

Picture by Denise Chan on Unsplash

Original image


<img src= "images/original.png" width=200 wide =200>

Red Filter


<img src= "images/red.png" width=200 wide =200>


Grey Filter


<img src= "images/grey.png" width=200 wide =200>


Invert Filter


<img src= "images/invert.png" width=200 wide =200>


Color swap


<img src= "images/swap.png" width=200 wide =200>

-------------------------------
Color binary


<img src= "images/binary.png" width=200 wide =200>

Binary rotate


<img src= "images/binary_rotate.gif" width=500 wide =500>

Black white rotate


<img src= "images/black_white_rotate.gif" width=500 wide =500>

Saturation

<img src= "images/saturation.gif" width=500 wide =500>


Color rotate


<img src= "images/rotate_colors.gif" width=500 wide =500>


##
Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 
  
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.YardenBarakDev:YB_Filters:1.0.0'
	}
  
  
  ## Usage
  

  ###### initiate 
  
  ```java
  
YBFilters ybFilters = new YBFilters(context); 
```

 ###### ask camera permission
   ```java
  
ybFilters.askCameraPermission(this); //activity 
```
note: you can use this code for the permission (the WRITE_EXTERNAL_STORAGE won't work on android 10+)

  ```java
  
 @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] results) {
        super.onRequestPermissionsResult(requestCode, permissions, results);
        if (!CameraPermissionHelper.hasCameraPermission(this)) {
            //Permission denied
            Toast.makeText(this, "Camera permission is needed to run this application", Toast.LENGTH_LONG)
                    .show();
            if (!CameraPermissionHelper.shouldShowRequestPermissionRationale(this)) {
                //Permission denied with checking "Do not ask again".
                CameraPermissionHelper.launchPermissionSettings(this);
            }
        }
    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            MAIN_Image.setImageURI(uri);
            MAIN_Image.setRotation(90);
            setRecyclerViewAdapter();
        }
    }
    
    private void dispatchTakePictureIntent() {

        if (!checkStoragePermission())
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        else{
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "NEW PICTURE");
            values.put(MediaStore.Images.Media.DESCRIPTION,"from camera");
            uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } catch (ActivityNotFoundException e) {
                // display error state to the user
            }
        }
    }

    public boolean checkStoragePermission(){
        return checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }
```
###### Choose filter
This library offer 16 diffrent built in filters
 BLUE
 RED 
 GREEN
 GREY 
 GREY_ON_BLUE
 GREY_ON_RED 
 GREY_ON_GREEN
 INVERT
 COLOR_SWAP
 COLOR_SWAP2
 ORIGINAL
 ALPHA_BLUE
 ALPHA_PINK 
 SEPIA 
 BINARY
 GREY_SCALE
 
  ```java
ybFilters.setFilter(imageview, YBFilters.Filters.GREY_ON_RED);
```
###### Special filters
Blur
you can control how blury the imgae will be by using a float between 0-25
  ```java
ybFilters.blurImage(image_background, radius);
```
Sharper image
  ```java
ybFilters.sharpenImage(image_background);
```
Glass image
  ```java
ybFilters.glassImage(image_background);
```
###### Upload image
You can upload an image on 3 ways
1. from drawable
2. from uri
3. from URL

```java
  ybFilters.uploadImageURL(imageview,"URL");
 
  ybFilters.uploadImageUri(imageview, uri);
  
  ybFilters.uploadImageDrawable(imageview, R.drawable.image);

```
###### Set adjust
This library offer 10 diffrent ways to adjust your image
SCALE
SATURATION
ROTATE_BLUE 
ROTATE_GREEN
ROTATE_RED 
SCALE_BLUE
SCALE_GREEN 
SCALE_RED
SEEK_BINARY 
SEEK_BLACK_WHITE 

```java
 ybFilters.adjust(image_background, YBFilters.Adjust.ROTATE_BLUE , progress);
```

###### Control the colors
this option give you the power to control on the amount of RGB in the photo. you can adjust how much green/blue/red to add or take from the image.
you will have to work with 6 integers between 0-255.
in my demo I used a 6 different SeekBars to control each integer seperatly. 

```java
    int mulRed = 0;
    int mulGreen= 0;
    int mulBlue= 0;
    int addRed= 0;
    int addGreen= 0;
    int addBlue= 0;
    
    ybFilters.manipulateColors(image_background , mulRed,  mulGreen,  mulBlue,  addRed,  addGreen,  addBlue);
```

###### Get all filters (without the special ones)
This option give you the option to get an ArrayList of filters which you can use to see all the filters in a scroll view

```java
    ArrayList<ColorMatrixColorFilter> filters = ybFilters.getAllFilters();
```

```java
 private void setRecyclerViewAdapter() {
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, filters, uri);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        Main_LST_images.setLayoutManager(linearLayoutManager);
        Main_LST_images.setAdapter(myRecyclerViewAdapter);
    }```

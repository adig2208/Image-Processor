# USEME - Image Processor Application Usage Instructions

## Running Script Commands:

### Running a Script at Program Start:

To execute a script upon starting the application, use the following syntax in your command
terminal:

Syntax: `java -jar ImageProcessor.jar -file <filename>`

1. Place your script file (e.g., `commandsforjar.txt`) in the `res` folder of your project.
2. Run the `jar` file by using the command `java -jar ImageProcessor.jar -file commandsforjar.txt`.
3. The application will sequentially execute commands from `commandsforjar.txt`, outputting results to the
   specified directory `(res\images)`, and then exit the program execution.

### Launching a Java Program with a Graphical User Interface

To launch a Java program with a graphical user interface (GUI), follow these steps:

Syntax: `java -jar Program.jar`

1. Make sure `ImageProcessor.jar` is a Java archive file containing your GUI-based application present.
2. Execute the `jar` file using the command `java -jar ImageProcessor.jar`. This can be done in a command-line interface like Terminal (on macOS/Linux) or Command Prompt/Powershell (on Windows).
3. Upon executing this command, the program's graphical user interface should automatically open. This is standard for GUI-based Java applications packaged in a JAR file.
4. This method is akin to double-clicking on the `ImageProcessor.jar` file in a graphical file manager. Most operating systems will run the JAR file using the `java -jar` command when double-clicked, opening the GUI.
5. Once open, you can interact with the application as intended, whether it's entering data, navigating through different windows or panels, or utilizing various features of the program.

### Image Processing Application - GUI Operations Guide

This guide outlines the steps for using the graphical user interface (GUI) of the image processing application to manipulate images and apply various effects.

### Launching the GUI

- Run the application by double-clicking the `ImageProcessor.jar` file or using the command `java -jar ImageProcessor.jar`.
- The GUI window will open, presenting a workspace with buttons for different image operations.

### Loading an Image

- Click the `Load Image` button to open a dialog box where you can navigate and select an image.
- The application supports four image formats(jpg, jpeg, png, ppm). 
- After selecting an image, it will display in the main area of the GUI and also the histogram for that image to the right.

### Applying Operations in the GUI

### Color Component Extraction

- **Red Component:**
  - Click the `Red Component` button to extract the red channel.
  - The red-only image will display on the left as a preview. 
  - Confirm to apply changes to the image and display its updated histogram on the right.
  - Cancel to revert to the original image.

- **Green Component:**
  - Click the `Green Component` button to extract the green channel.
  - The green-only image will display on the left as a preview.
  - Confirm to apply changes to the image and display its updated histogram on the right.
  - Cancel to revert to the original image.

- **Blue Component:**
  - Click the `Blue Component` button to extract the blue channel.
  - The blue-only image will display on the left as a preview.
  - Confirm to apply changes to the image and display its updated histogram on the right.
  - Cancel to revert to the original image.

### Flip Operations

- **Horizontal Flip:**
  - Click the `Horizontal Flip` button to flip the image horizontally.
  - The horizontally flipped image will display on the left as a preview.
  - Confirm to apply changes to the image and display its updated histogram on the right.
  - Cancel to revert to the original image.

- **Vertical Flip:**
  - Click the `Vertical Flip` button to flip the image vertically.
  - The vertically flipped image will display on the left as a preview.
  - Confirm to apply changes to the image and display its updated histogram on the right.
  - Cancel to revert to the original image.

### Blur Operation

- Click the `Blur` button.
- A dialog box prompts for a split percentage.
- Enter a value and click `OK` to preview the blur effect applied to a portion of the image.
- Confirm to apply changes, and you can see the blurred image on the left with its histogram on the right.
- Cancel to revert to the original image.
- After canceling if you click the button again you can enter the split percentage and repeat the process.

### Sharpen Operation

- Click the `Sharpen` button.
- A dialog box prompts for a split percentage.
- Enter a value and click `OK` to preview the sharpening effect applied to a portion of the image.
- Confirm to apply changes, and you can see the sharpened image on the left with its histogram on the right.
- Cancel to revert to the original image.
- After canceling if you click the button again you can enter the split percentage and repeat the process.

### Luma Operation

- Click the `Luma` button to convert the image to grayscale based on the luma component.
- A dialog box prompts for a split percentage.
- Enter a value and click `OK` to preview the luma effect applied to a portion of the image.
- Confirm to apply changes, and you can see the Luma image on the left with its histogram on the right.
- Cancel to revert to the original image.
- After canceling if you click the button again you can enter the split percentage and repeat the process.

### Sepia Operation

- Click the `Sepia` button to apply a sepia tone.
- A dialog box prompts for a split percentage.
- Enter a value and click `OK` to preview the sepia effect applied to a portion of the image.
- Confirm to apply changes, and you can see the Sepia image on the left with its histogram on the right.
- Cancel to revert to the original image.
- After canceling if you click the button again you can enter the split percentage and repeat the process.

### Compress Image

- Click the Compress Image button to initiate the compression adjustment.
- You will be prompted to specify the compression percentage.
- Enter the desired percentage and confirm to view the compressed image on the left.
- Confirm to apply changes to the image and display its updated histogram on the right.
- Cancel to revert to the original image.

### Color Correction Operation

- Click the `Color Correct` button for color balance adjustments.
- A dialog box prompts for a split percentage.
- Enter a value and click `OK` to preview the color correction applied to a portion of the image.
- Confirm to apply changes, and you can see the color corrected image on the left with its histogram on the right.
- Cancel to revert to the original image.
- After canceling if you click the button again you can enter the split percentage and repeat the process.

Each of these operations provides a hands-on approach to image editing, with real-time previews and flexible control over the final output.

### Adjusting Levels

- Click the `Adjust Levels` button to trigger a prompt for Black (b), Mid (m), and White (w) values.
- A dialog box prompts for a split percentage.
- Enter a value and click `OK` to preview the level adjustment applied to a portion of the image.
- Confirm to apply changes, and you can see the level adjustment image on the left with its histogram on the right.
- Cancel to revert to the original image.
- After canceling if you click the button again, you can enter the b, m, w values and split percentage and repeat the process.
- Enter the values and confirm to see the adjusted image and its histogram.

### Saving the Image

- Click the `Save` button to save the processed image.
- This will give you an option to choose a location to save the image and also choose a format to save the image(jpg, jpeg, png, ppm).
- The application will loop back, allowing you to load and process another image.

### Key Features and Alerts

- **Unsaved Changes Alert:** If you attempt to load a new image without saving the current one, a popup will alert you about unsaved changes. You can choose to save them or proceed without saving.
- **Value Errors:** If an illegal argument exception or IO exception occurs during an operation, a popup box will display the error, and the operation will be halted.

By following these steps, you can navigate through the application's GUI and explore its image processing capabilities.

### Running a Script After Program Start:

You can also run a script after the application has started by following these steps:

Syntax: `java -jar Program.jar -text`

1. Place your script file (e.g., `commands.txt` or `commandsforjar.txt`) in the `res` folder of your project.
2. Run the `jar` file by using the command `java -jar ImageProcessor.jar -text`.
3. When prompted for commands, enter: `run commandsforjar.txt` if you executed the jar file OR
   enter: `run res/commands.txt` (Windows: `run res\commands.txt`) if you executed the above command.
4. The application will sequentially execute commands from `commands.txt` or `commandsforjar.txt`, outputting results to the
   specified directory`(res\images)`.

## Running Individual Commands via CLI:

Run the `jar` file by using the command `java -jar ImageProcessor.jar -text`, and input commands as prompted. Below are the supported commands:

### Load Command:

- **Syntax**: `load <file path> <image name>`
- **Description**: Loads an image from the specified file path and assigns it an alias.
- **Example**: `load res/images/film_original.jpg film`

### Component Extraction Commands:

- **Syntax**: `<color-component> <source image name> <destination image name>`
- **Description**: Extracts a single RGB color component.
- **Examples**:
    - `red-component film filmRedComponent`
    - `green-component film filmGreenComponent`
    - `blue-component film filmBlueComponent`

### Sepia Command:

- **Syntax**: `sepia <source image name> <destination image name>`
- **Description**: Applies a sepia tone effect.
- **Example**: `sepia film filmSepia`

### Blur Command:

- **Syntax**: `blur <source image name> <destination image name>`
- **Description**: Applies a blur effect.
- **Example**: `blur film filmBlur`

### Sharpen Command:

- **Syntax**: `sharpen <source image name> <destination image name>`
- **Description**: Increases image sharpness.
- **Example**: `sharpen film filmSharpen`

### Greyscale Commands:

- **Syntax**: `<greyscale-method>-component <source image name> <destination image name>`
- **Description**: Converts to greyscale.
- **Examples**:
    - `value-component film filmValue`
    - `luma-component film filmLuma`
    - `intensity-component film filmIntensity`

### Flip Commands:

- **Syntax**: `<orientation-flip> <source image name> <destination image name>`
- **Description**: Flips the image.
- **Examples**:
    - `horizontal-flip film filmHFlip`
    - `vertical-flip film filmVFlip`

### Brighten/Darken Command:

- **Syntax**: `brighten <value> <source image name> <destination image name>`
- **Description**: Adjusts brightness.
- **Examples**:
    - `brighten 70 film filmBrighter`
    - `brighten -70 film filmDarker`

### RGB Split and Combine Commands:

- **Syntax**: `rgb-split <source image name> <red dest> <green dest> <blue dest>`
- **Description**: Splits into RGB components.
- **Example**: `rgb-split film redSplit greenSplit blueSplit`

- **Syntax**: `rgb-combine <dest image name> <red source> <green source> <blue source>`
- **Description**: Combines RGB components.
- **Example**: `rgb-combine filmCombined redSplit greenSplit blueSplit`

### Save Command:

- **Syntax**: `save <file path> <image name>`
- **Description**: Saves the image.
- **Example**: `save res/savedImage.jpg myImage`

### Additional Commands:

### Color Correction Command:

- **Syntax**: `color-correct <source image name> <destination image name>`
- **Description**: Adjusts the color balance of the image to correct any color casts and achieve a
  more natural look.
- **Example**: `color-correct film filmColorCorrect`

### Levels Adjustment Command:

- **Syntax
  **: `levels-adjust <black point value> <mid point value> <white point value> <source image name> <destination image name>`
- **Description**: Alters the shadows, midtones, and highlights of an image based on the provided
  black, mid, and white point values.
- **Example**: `levels-adjust 20 100 255 film filmLevelsAdjust`

### Compression Command:

- **Syntax**: `compress <percentage> <source image name> <destination image name>`
- **Description**: Reduces the image file size by the specified percentage through compression,
  which may affect image quality.
- **Example**: `compress 20 film filmCompress20`

### Histogram Creation Command:

- **Syntax**: `histogram <source image name> <destination image name>`
- **Description**: Generates a histogram image representing the distribution of pixel intensities in
  the source image.
- **Example**: `histogram film filmHist`

### Split Effect Commands:

- **Description**: The following commands can apply effects to a portion of the image.
- **Supported Commands
  **: `blur`, `sharpen`, `sepia`, `value`, `luma`, `intensity`, `color-correct`, `levels-adjust`
- **Syntax**: `<command> <source image name> <destination image name> split <percentage>`
- **Examples**:
    - `sepia film filmSepiaSplit50 split 50`
    - `blur film filmBlurSplit30 split 30`
    - `luma-component film filmLumaSplit60 split 60`

Please replace `<file path>`, `<image name>`, `<destination image name>`, and `<value>` with actual
paths, names, or numbers relevant to your images and the changes you want to make. Ensure that the
file paths used are valid and accessible from your application's current working directory.
Ensure to replace placeholders with actual values. The `<value>` for brightness can be positive (to
brighten) or negative (to darken). The `<percentage>` for split effect commands defines the
proportion of the image affected by the effect.

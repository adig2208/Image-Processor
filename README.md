# Image Processing Application

This image processing application follows an MVC architecture. The src folder contains all the code which has been organized into different packages to adhere to the MVC principles.
# SRC Folder: MVC Architecture

## Model:
The `Model` represents all the components that define the 'Model' in the MVC architecture.

### IImageModel.java:
An interface defining the model for the image processing application, detailing methods for various image processing operations.

- **Package**: model

- **Methods**:
  - `addImage(Image image, String imageName)`: Loads and names an image into the model.
  - `getImage(String imageName)`: Fetches an image by its assigned name.
  - `redComponent(String imageName, String destImageName)`: Isolates the red component from an image.
  - `greenComponent(String imageName, String destImageName)`: Isolates the green component from an image.
  - `blueComponent(String imageName, String destImageName)`: Isolates the blue component from an image.
  - `valueComponent(String imageName, String destImageName, Optional<Double> splitPercentageOpt)`: Isolates the value component used in grayscale conversion.
  - `lumaComponent(String imageName, String destImageName, Optional<Double> splitPercentageOpt)`: Isolates the luma component used in grayscale conversion.
  - `intensityComponent(String imageName, String destImageName, Optional<Double> splitPercentageOpt)`: Isolates the intensity component used in grayscale conversion.
  - `horizontalFlip(String imageName, String destImageName)`: Flips an image along the horizontal axis.
  - `brightenCommand(int increment, String imageName, String destImageName)`: Modifies the brightness of an image.
  - `blur(String imageName, String destImageName, Optional<Double> splitPercentageOpt)`: Applies a blur effect on the image.
  - `sharpen(String imageName, String destImageName, Optional<Double> splitPercentageOpt)`: Intensifies the details in an image.
  - `verticalFlip(String imageName, String destImageName)`: Flips an image along the vertical axis.
  - `sepia(String imageName, String destImageName,Optional<Double> splitPercentageOpt)`: Applies a sepia tint to the image.
  - `rgbSplit(String imageName, String destImageNameRed, String destImageNameGreen, String destImageNameBlue)`: Dismantles an image into its RGB channels.
  - `rgbCombine(String redImageName, String greenImageName, String blueImageName, String destImageName)`: Merges separate RGB images into one.
  - `histogram(String imageName)`: Generates a histogram representation of an image.
  - `colorCorrect(String imageName, String destImageName, Optional<Double> splitPercentageOpt)`: Performs color correction on the image.
  - `adjustLevels(String imageName, String destImageName, int b, int m, int w, Optional<Double> splitPercentageOpt)`: Adjusts the brightness, midtones, and white levels of the image.
  - `compressImage(String imageName, String destImageName, int percentage)`: Compresses the image by a specified percentage.

### Design Changes and Justifications:

- **Introduction of Optional Split View**:
  The `IImageModel` interface has been updated to accommodate an optional split view functionality through the use of the `Optional<Double> splitPercentageOpt` parameter in various methods. This change allows for the application of image processing operations, such as grayscale conversion and sepia tone application, to only a specified portion of the image, enhancing flexibility and user control.

  **Justification**:
  - _Enhanced Flexibility_: The introduction of an optional split percentage parameter allows users to apply effects to only a part of the image, offering greater creative control.
  - _Adherence to Open-Closed Principle_: The model is now open for extension but closed for modification, meaning that new functionalities (like split view) can be added without altering the existing code structure.
  - _Integration with Strategy Pattern_: By incorporating the `Optional<Double>` parameter, the model's methods are more aligned with the Strategy pattern implemented in the `model.strategy` package, particularly with the `SplitFilterDecorator` class, which requires a split percentage to function.
  - _Consistency_: Applying a consistent approach to optional parameters across various methods ensures that the interface remains uniform and predictable for developers implementing the model.

### ImageModel.java:
A concrete implementation of `IImageModel.java` which manages and processes images within the application. It implements all methods from `IImageModel.java`.

- **Package**: model

- **Fields**:
  - `imageMap`: A `Map` structure (specifically, a `HashMap`) that maintains all the images within the model. The image name acts as the key (String) and the image itself as the value (`Image`).

- **Responsibilities**: 
  - The `ImageModel` is responsible for adding and retrieving `Image` objects from the `imageMap`. Image processing operations are not handled directly by the `ImageModel`; instead, they are performed by calling methods on the `Image` objects themselves. This adheres to the principles of object-oriented design by keeping the `ImageModel` focused on managing the collection of `Image` objects, while the `Image` class encapsulates the details of image processing.
      
The method fetches the image from the `imageMap` using the provided `imageName` and then applies the desired operation based on the given `command`. If the image is not found, an `IOException` with a message "Image not found." is thrown.

### Design Changes and Justifications:

- **Refactoring to Image Class**: Initially, the `ImageModel` class contained the logic for image processing operations. This design was changed to move the image processing logic into the `Image` class.

  **Justification**:
  - _Encapsulation_: By shifting the logic to the `Image` class, we encapsulate the behavior with the data it manipulates, adhering to OOP principles.
  - _Cohesion_: The `Image` class is now more cohesive, as it contains all the operations that directly pertain to image data.
  - _Separation of Concerns_: `ImageModel` is now solely responsible for managing the storage and retrieval of `Image` objects, while `Image` handles the processing. This separation allows for clearer organization and the potential for easier testing and maintenance.

### Image.java:
Represents a two-dimensional image composed of pixels. This class provides extensive methods for manipulating and querying image data and encapsulates all image processing logic.

- **Package**: `model.image`

- **Fields**:
  - `pixels`: A 2D array of `Pixel` objects representing the image's pixel data.

- **Constructor**:
  - `Image(Pixel[][] pixels)`: Initializes an `Image` object with a 2D array of `Pixel` objects.

- **Core Methods**:
  - `getPixels()`: Returns the 2D pixel array.
  - `getPixel(int x, int y)`: Retrieves a pixel at specified coordinates.
  - `getWidth()`: Gets the image's width.
  - `getHeight()`: Gets the image's height.
  - `setPixel(int x, int y, Pixel pixel)`: Sets a pixel at specified coordinates.

- **Image Processing Methods**:
  - `horizontalFlip()`: Returns a horizontally flipped copy of the image.
  - `verticalFlip()`: Returns a vertically flipped copy of the image.
  - `brighten(int increment)`: Brightens or darkens the image.
  - `applyKernel(double[][] kernel)`: Applies a convolution kernel to the image.
  - `blur()`: Blurs the image.
  - `sharpen()`: Sharpens the image.
  - `extractRedComponent()`: Extracts the red channel.
  - `extractGreenComponent()`: Extracts the green channel.
  - `extractBlueComponent()`: Extracts the blue channel.
  - `toValueComponent()`: Converts to grayscale using the value component.
  - `toLumaComponent()`: Converts to grayscale using the luma component.
  - `toIntensityComponent()`: Converts to grayscale using the intensity component.
  - `toSepia()`: Applies a sepia tone to the image.
  - `combineColorChannels(Image redImage, Image greenImage, Image blueImage)`: Combines separate RGB channel images into one image.
  - `calculateHistograms()`: Calculates histograms for the RGB channels.
  - `colorCorrect()`: Performs color correction.
  - `adjustLevels(int b, int m, int w)`: Adjusts the levels for brightness, midtones, and highlights.
  - `compress(int percentage)`: Compresses the image by a percentage.
  - `applyFilter(FilterStrategy filterStrategy)`: Applies a filter strategy to the image.

- **Private Helper Methods**:
  - `truncate(double[][] channel, double threshold)`: Applies a threshold to truncate small coefficients.
  - `transpose(double[][] matrix)`: Transposes a 2D matrix.

This `Image` class serves as the central hub for image processing tasks, adhering to object-oriented principles by encapsulating image data and related behaviors.

### Pixel.java:
Describes a single pixel in an image using RGB color components. Each component (red, green, and blue) has a value that can range from 0 to 255.

- **Package**: model.image

- **Fields**:
  - `red`: An integer value representing the red component of the pixel.
  - `green`: An integer value representing the green component of the pixel.
  - `blue`: An integer value representing the blue component of the pixel.

- **Methods**:
  - **Constructor**: `Pixel(int red, int green, int blue)`: Constructs a `Pixel` object using the provided RGB values. Each value is clamped to ensure it lies between 0 and 255.
  - `clamp(int value)`: A private method that restricts the given value to lie between 0 and 255.
  - `getRed()`: Retrieves the red color component of the pixel.
  - `getGreen()`: Retrieves the green color component of the pixel.
  - `getBlue()`: Retrieves the blue color component of the pixel.

### HaarWaveletTransform.java:
This class is dedicated to performing the Haar Wavelet Transform and its inverse on image data. It facilitates image compression by converting the image data into a frequency domain, enabling selective coefficient truncation for data reduction.

- **Package**: `model`

- **Key Functionalities**:
  - Performs forward and inverse Haar Wavelet Transform on 2D matrices.
  - Applies thresholding to truncate small coefficients during compression.
  - Supports padding and unpadding operations to handle image dimensions not in powers of two.

- **Methods**:
  - `avgAndDiff(List<Double> s)`: Calculates averages and differences for Haar Wavelet Transform.
  - `invAvgAndDiff(List<Double> s)`: Reverts the averages and differences to reconstruct the original data.
  - `padArr(double[][] X)`: Pads a 2D array to make it square with dimensions as powers of two.
  - `powerOfTwo(int number)`: Calculates the next power of two greater than or equal to a given number.
  - `unpadArr(double[][] X, int originalWidth, int originalHeight)`: Removes padding to revert to original dimensions.
  - `transform(List<Double> s, int l)`: Applies the Haar Wavelet Transform to a list.
  - `invert(List<Double> transformedSequence, int l)`: Applies the inverse Haar Wavelet Transform to a list.
  - `haar(double[][] mat)`: Applies the Haar Wavelet Transform to a 2D matrix.
  - `invHaar(double[][] mat, int originalWidth, int originalHeight)`: Applies the inverse Haar Wavelet Transform to a 2D matrix.
  - `calThreshold(double[][] redChannel, double[][] greenChannel, double[][] blueChannel, double percentage)`: Calculates the threshold for truncating values in compression.

The `HaarWaveletTransform` class provides a robust toolkit for handling image compression tasks within the application, adhering to the principles of image processing and data compression algorithms.

### Strategy Package (`model.strategy`):

This package is part of the model and includes classes that implement the Strategy pattern for image processing. It allows for the dynamic application of various filters and effects to images, such as blur, sharpen, sepia, and more, potentially with support for a split view.

### Design Justifications:

The Strategy package is an integral part of the model layer in the image processing application. It employs the Strategy design pattern to encapsulate various image filtering algorithms behind a common interface. This pattern is highly beneficial for the following reasons:

- _Flexibility_: The Strategy pattern allows for easy swapping of algorithms at runtime, providing a flexible mechanism to change image processing behaviors without altering the client code.
- _Extensibility_: New filtering strategies can be added without disturbing existing code, making the system easy to extend.
- _Maintainability_: Each filter strategy is maintained within its own class, promoting a cleaner and more modular design which in turn makes the codebase easier to understand and maintain.
- _Testability_: Individual strategies can be tested in isolation, ensuring that each filter behaves as expected.

### FilterStrategy.java:
Defines the strategy interface for applying various filters to an image. Implementations of this interface encapsulate specific image filtering algorithms.

- **Package**: model.strategy

- **Methods**:
  - `apply(Image image)`: Applies the filter strategy to the provided image and returns a new `Image` instance with the filter applied.

### SplitFilterDecorator.java:
Implements the `FilterStrategy` interface to apply a split-view effect to image filters. It decorates an existing filter strategy, applying it to only a portion of the image as specified by a percentage split point.

- **Package**: model.strategy

- **Fields**:
  - `originalStrategy`: The original `FilterStrategy` that is being decorated.
  - `splitPercentage`: A double value representing the percentage of the image width to apply the original strategy.

- **Methods**:
  - **Constructor**: `SplitFilterDecorator(FilterStrategy strategy, double splitPercentage)`: Initializes a new instance of `SplitFilterDecorator` with a given filter strategy and a split percentage.
  - `apply(Image originalImage)`: Overrides the `apply` method to apply the filter strategy to the image up to the specified split point. Pixels to the left of the split point are processed, while pixels to the right remain unchanged. Returns a new `Image` instance with the filter applied partially.

### AdjustLevelsFilterStrategy.java:
Implements the `FilterStrategy` interface to perform levels adjustment on images. The strategy adjusts the intensity distribution of the shadows, midtones, and highlights based on the provided parameters.

- **Package**: model.strategy

- **Fields**:
  - `b`: Integer value representing the black point for shadows adjustment (0-255).
  - `m`: Integer value representing the midpoint for midtones adjustment (0-255).
  - `w`: Integer value representing the white point for highlights adjustment (0-255).

- **Methods**:
  - **Constructor**: `AdjustLevelsFilterStrategy(int b, int m, int w)`: Creates an instance of `AdjustLevelsFilterStrategy` with specified levels for shadows (b), midtones (m), and highlights (w). The values must follow the order `b` < `m` < `w` and be within the range [0, 255].
  - `apply(Image image)`: Applies the levels adjustment to the provided image based on the initial black point, midpoint, and white point values. It returns a new `Image` object with the modified intensity levels.

### BlurFilterStrategy.java:
Defines a strategy for applying a blur effect to an image. It encapsulates the blurring algorithm by utilizing the `blur` method defined in the `Image` class.

- **Package**: model.strategy

- **Methods**:
  - `apply(Image image)`: Takes an `Image` object as input and returns a new `Image` instance that has been blurred. This method calls the `blur` method of the `Image` class to perform the actual blurring operation.

### ColorCorrectFilterStrategy.java:
Implements a strategy for color correction in images, part of the model's strategy pattern. It leverages the `colorCorrect` method from the `Image` class to adjust the color balance.

- **Package**: model.strategy

- **Methods**:
  - `apply(Image image)`: Accepts an `Image` object and returns a new `Image` instance with color correction applied. The color correction process is handled by invoking the `colorCorrect` method on the `Image` class.

### IntensityFilterStrategy.java:
This class is part of the strategy pattern in the model package that applies an intensity-based filter to an image. It converts the image to grayscale by averaging the RGB color channels.

- **Package**: model.strategy

- **Methods**:
  - `apply(Image image)`: Takes an `Image` object and returns a new `Image` instance converted to grayscale using the intensity method. The conversion leverages the `toIntensityComponent` method from the `Image` class.

### LumaFilterStrategy.java:
Part of the model's strategy pattern, this class applies a luma-based filter to convert an image into a grayscale version. It calculates the luma component as a weighted sum of the RGB color channels.

- **Package**: model.strategy

- **Methods**:
  - `apply(Image image)`: Accepts an `Image` object and uses the `toLumaComponent` method of the `Image` class to return a new `Image` instance in grayscale. The luma-based conversion considers the relative luminance of the color channels to produce the grayscale image.

### SepiaFilterStrategy.java:
This class is an implementation of the `FilterStrategy` interface and is responsible for converting an image to a sepia tone. It leverages the `toSepia` method from the `Image` class to achieve the sepia effect.

- **Package**: model.strategy

- **Methods**:
  - `apply(Image image)`: Takes an `Image` object as input and calls the `toSepia` method on it. The result is a new `Image` object that has been processed to have a sepia tone, giving it a warm, brownish-gray color typical of historic photographs.

### SharpenFilterStrategy.java:
This class implements the `FilterStrategy` interface to apply a sharpening filter to an image. The sharpening process enhances the edges and details within the image, making it appear more crisp and defined.

- **Package**: model.strategy

- **Methods**:
  - `apply(Image image)`: Takes an `Image` object as input and applies a sharpening filter by invoking the `sharpen` method from the `Image` class. The resulting `Image` object is returned with enhanced definition.

### ValueFilterStrategy.java:
This class provides an implementation of the `FilterStrategy` interface to convert an image to its value component. The value component is derived from the highest values among the three RGB color channels.

- **Package**: model.strategy

- **Methods**:
  - `apply(Image image)`: Accepts an `Image` object and applies a value filter, extracting the highest value from the red, green, and blue color channels for each pixel. It utilizes the `toValueComponent` method from the `Image` class to produce a grayscale image based on the value component. The resultant `Image` object, now reflecting the value component of the original image, is returned.

## Controller:
The `Controller` represents all the components that define the 'Controller' in the MVC architecture.

### ICommand.java:
An interface for the commands supported by the image processing application.

- **Package**: controller.commands

- Methods:
  - `execute()`: Executes the image processing command and returns a boolean value indicating whether the operation was successful or not.

### AbstractCommand.java:
Represents an abstract command that operates on an image within a model. This class provides the foundational structure for commands that process an image.

- **Package**: controller.commands

- **Fields**:
  - `model`: A reference to the image model.
  - `imageName`: The name of the image to be processed.

- **Methods**:
  - **Constructor**: `AbstractCommand(String imageName, IImageModel model)`: Constructs the command with the provided image name and a reference to the model.
  - `execute()`: Executes the image processing command, attempting to process the image. Returns a boolean value indicating the success or failure of the operation.
  - `processImage()`: An abstract method that processes the image. Derived classes should override this method to provide their specific image processing logic. Throws an exception if an error occurs during image processing.
  - `toString()`: Overrides the `toString` method to return the class name of that respective class.

### AbstractCombineSplitCommand.java:
Extends `AbstractCommand.java` and represents an abstract command for operations that involve combining or splitting the RGB channels of an image. This class provides a foundation for commands that work with separate red, green, and blue channel images.

- **Package**: controller.commands

- **Fields**:
  - `redImageName`: The name of the image representing the red channel.
  - `greenImageName`: The name of the image representing the green channel.
  - `blueImageName`: The name of the image representing the blue channel.

- **Methods**:
  - **Constructor**: `AbstractCombineSplitCommand(String imageName, String redImageName, String greenImageName, String blueImageName, IImageModel model)`: Constructs the command with provided image names for the original image and the red, green, and blue channels. Also takes a reference to the image model.

### AbstractLoaderSaverCommand.java:
Extends `AbstractCommand.java` and provides foundational functionalities for loading and saving images from/to various file formats. This class encapsulates the logic for determining file extensions and associating them with the appropriate image file parsers, such as PNG, JPG, JPEG, and PPM.

- **Package**: controller.commands

- **Fields**:
  - `imagePath`: The path to the image file.

- **Methods**:
  - **Constructor**: `AbstractLoaderSaverCommand(String imagePath, String imageName, IImageModel model)`: Constructs the command with the provided image path, image name, and a reference to the model.
  - `getFileExtension(String path)`: Extracts the file extension from the provided path.
  - `getImageObject(String path)`: Returns the appropriate image file parser based on the file extension.

### AbstractTransformCommand.java:
Extends `AbstractCommand.java` and provides foundational functionalities for transforming images. This class offers a foundational structure for commands that need to transform an image.

- **Package**: controller.commands

- **Fields**:
  - `destImageName`: The name of the transformed destination image.

- **Methods**:
  - **Constructor**: `AbstractTransformCommand(String imageName, String destImageName, IImageModel model)`: Constructs the command with the provided source image name, destination image name, and a reference to the model.

### AbstractSplitCommand.java:
A specialized abstract command that extends `AbstractTransformCommand` with added functionality to handle image transformations with an optional split effect. This command forms the basis for commands that require a split view of the transformation.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `AbstractSplitCommand(String imageName, String destImageName, IImageModel model, Optional<Double> splitPercentage)`: Creates an abstract command for transforming images with the ability to apply the transformation up to a specified split percentage. The constructor requires the name of the source image, the name for the transformed destination image, the image model, and an optional split percentage.
  - `processImage()`: This abstract method must be implemented by subclasses to specify the image processing behavior. It should apply the desired transformation to the image and respect the split percentage if provided.

### LoadCommand.java:
Extends `AbstractLoaderSaverCommand.java` and is tasked with loading an image into the model from a specified file path. This class encapsulates the command to retrieve an image from the given path and load it into the model under a provided name.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `LoadCommand(String imagePath, String imageName, IImageModel model)`: Instantiates a new `LoadCommand` with the specified image path, image name, and model.
  - `processImage()`: Implements the abstract method from `AbstractLoaderSaverCommand`. It retrieves an image using an `IImageFileParser` from the given path and adds it to the model with the specified image name. Throws an exception if an error occurs during the loading process.

### SaveCommand.java:
Extends `AbstractLoaderSaverCommand.java` and handles the task of saving an image from the model to a specified file path. It ensures that the image with the given name is saved using an image parser which manages the file format and the writing process.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `SaveCommand(String imagePath, String imageName, IImageModel model)`: Initializes a new `SaveCommand` with the given path, image name, and model reference.
  - `processImage()`: Implements the abstract method from `AbstractLoaderSaverCommand`. It calls on an `IImageFileParser` to save the specified image to the provided path. An exception is thrown if there is an error during the saving process.

### RedComponentCommand.java:
Extends `AbstractTransformCommand.java` and is designed to process and extract the red component from an image. Through this class, the model is directed to execute the appropriate image processing task as per the user's command.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `RedComponentCommand(String imageName, String destImageName, IImageModel model)`: Constructs the command with the provided source image name, destination image name, and a reference to the image model.
  - `processImage()`: Overrides the abstract method from `AbstractTransformCommand.java`. It uses the underlying image model to extract the red component of the source image and saves the resultant image with the new name.

### BlueComponentCommand.java:
Extends `AbstractTransformCommand.java` and is designed to process and extract the blue component from an image. Through this class, the model is directed to execute the appropriate image processing task as per the user's command.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `BlueComponentCommand(String imageName, String destImageName, IImageModel model)`: Constructs the command with the provided source image name, destination image name, and a reference to the image model.
  - `processImage()`: Overrides the abstract method from `AbstractTransformCommand.java`. It uses the underlying image model to extract the blue component of the source image and saves the resultant image with the new name.

### GreenComponentCommand.java:
Extends `AbstractTransformCommand.java` and is designed to process and extract the green component from an image. Through this class, the model is directed to execute the appropriate image processing task as per the user's command.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `GreenComponentCommand(String imageName, String destImageName, IImageModel model)`: Constructs the command with the provided source image name, destination image name, and a reference to the image model.
  - `processImage()`: Overrides the abstract method from `AbstractTransformCommand.java`. It uses the underlying image model to extract the green component of the source image and saves the resultant image with the new name.

### ValueComponentCommand.java:
It extends `AbstractSplitCommand` to extract the value component from an image and optionally incorporate a split effect where part of the image remains unaltered. Through this class, the model is directed to execute the appropriate image processing task as per the user's command. The split percentage determines the extent of the image to which the value conversion will be applied.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `ValueComponentCommand(String imageName, String destImageName, IImageModel model, Optional<Double> splitPercentage)`: Constructs the command with the provided source image name, destination image name, and a reference to the image model.
  - `processImage()`: Overrides the abstract method from `AbstractTransformCommand.java`. It uses the underlying image model to extract the value component of the source image and saves the resultant image with the new name.

### LumaComponentCommand.java:
It extends `AbstractSplitCommand` to extract the luma component from an image and optionally incorporate a split effect where part of the image remains unaltered. Through this class, the model is directed to execute the appropriate image processing task as per the user's command. The split percentage determines the extent of the image to which the luma conversion will be applied.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `LumaComponentCommand(String imageName, String destImageName, IImageModel model, Optional<Double> splitPercentage)`: Constructs the command with the provided source image name, destination image name, and a reference to the image model.
  - `processImage()`: Overrides the abstract method from `AbstractTransformCommand.java`. It uses the underlying image model to extract the luma component of the source image and saves the resultant image with the new name.

### IntensityComponentCommand.java:
It extends `AbstractSplitCommand` to extract the intensity component from an image and optionally incorporate a split effect where part of the image remains unaltered. Through this class, the model is directed to execute the appropriate image processing task as per the user's command. The split percentage determines the extent of the image to which the intensity conversion will be applied.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `IntensityComponentCommand(String imageName, String destImageName, IImageModel model, Optional<Double> splitPercentage)`: Constructs the command with the provided source image name, destination image name, and a reference to the image model.
  - `processImage()`: Overrides the abstract method from `AbstractTransformCommand.java`. It uses the underlying image model to extract the intensity component of the source image and saves the resultant image with the new name.

### SepiaCommand.java:
It extends `AbstractSplitCommand` to get sepia toned version of an image and optionally incorporate a split effect where part of the image remains unaltered. This class uses the provided image model to manage the sepia transformation of the source image and stores the resultant image with a new name. The split percentage determines the extent of the image to which the sepia will be applied.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `SepiaCommand(String imageName, String destImageName, IImageModel model, Optional<Double> splitPercentage)`: Constructs the command with the specified source image name to be converted to sepia, the name where the sepia-toned image will be stored, and a reference to the image model.
  - `processImage()`: Overrides the abstract method from `AbstractTransformCommand.java`. It uses the underlying image model to apply a sepia tone on the source image and saves the resultant sepia-toned image with the new name.

### BlurCommand.java:
It extends `AbstractSplitCommand` to apply blur effect on an image and optionally incorporate a split effect where part of the image remains unaltered. Through this class, the model is directed to execute the appropriate image processing task as per the user's command.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `BlurCommand(String imageName, String destImageName, IImageModel model, Optional<Double> splitPercentage)`: Constructs the command with the provided source image name to be blurred, destination image name after blurring, and a reference to the image model. The split percentage determines the extent of the image to which the blur effect will be applied.
  - `processImage()`: Overrides the abstract method from `AbstractTransformCommand.java`. It uses the underlying image model to blur the source image and saves the resultant blurred image with the new name.

### SharpenCommand.java:
It extends `AbstractSplitCommand` to apply a sharpen effect on an image and optionally incorporate a split effect where part of the image remains unaltered. This class uses the provided image name and destination name, and the image model manages the actual transformation.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `SharpenCommand(String imageName, String destImageName, IImageModel model, Optional<Double> splitPercentage)`: Constructs the command with the specified source image name to be sharpened, the name where the sharpened image will be stored, and a reference to the image model. The split percentage determines the extent of the image to which the sharpening will be applied.
  - `processImage()`: Overrides the abstract method from `AbstractTransformCommand.java`. It uses the underlying image model to sharpen the source image and saves the resultant sharpened image with the new name.

### BrightenCommand.java:
Extends `AbstractTransformCommand.java` and is designed to brighten an image by a specified increment. Through this class, the model is directed to execute the appropriate image processing task as per the user's command.

- **Package**: controller.commands

- **Fields**:
  - `increment`: Specifies the amount by which the image should be brightened.

- **Methods**:
  - **Constructor**: `BrightenCommand(int increment, String imageName, String destImageName, IImageModel model)`: Constructs the command with the specified brightening increment, source image name to be brightened, destination image name after brightening, and a reference to the image model.
  - `processImage()`: Overrides the abstract method from `AbstractTransformCommand.java`. It uses the underlying image model to brighten the source image by the specified increment and saves the resultant brightened image with the new name.

### HorizontalFlipCommand.java:
Extends `AbstractTransformCommand.java` and is designed to horizontally flip an image. Through this class, the model is directed to execute the appropriate image processing task as per the user's command.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `HorizontalFlipCommand(String imageName, String destImageName, IImageModel model)`: Constructs the command with the specified source image name to be flipped, destination image name after flipping, and a reference to the image model.
  - `processImage()`: Overrides the abstract method from `AbstractTransformCommand.java`. It uses the underlying image model to apply a horizontal flip to the source image and saves the resultant flipped image with the new name.

### VerticalFlipCommand.java:
Extends `AbstractTransformCommand.java` and is designed to vertically flip an image. Through this class, the model is directed to execute the appropriate image processing task as per the user's command.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `VerticalFlipCommand(String imageName, String destImageName, IImageModel model)`: Constructs the command with the specified source image name to be flipped, destination image name after flipping, and a reference to the image model.
  - `processImage()`: Overrides the abstract method from `AbstractTransformCommand.java`. It uses the underlying image model to apply a vertical flip to the source image and saves the resultant flipped image with the new name.

### RGBSplitCommand.java:
Extends `AbstractCombineSplitCommand.java` and is designed to split an RGB image into its red, green, and blue components. Through this class, the model is directed to execute the appropriate image processing task as per the user's command.

- **Package**: controller.commands

- **Fields**:
  - `imageName`: Specifies the name of the source RGB image to be split.
  - `redImageName`: Destination name for the red component of the image.
  - `greenImageName`: Destination name for the green component of the image.
  - `blueImageName`: Destination name for the blue component of the image.

- **Methods**:
  - **Constructor**: `RGBSplitCommand(String ImageName, String redImageName, String greenImageName, String blueImageName, IImageModel model)`: Constructs the command with the provided image names for the destination RGB channels and a reference to the image model.
  - `processImage()`: Overrides the abstract method from `AbstractCombineSplitCommand.java`. It uses the underlying image model to split the source RGB image into its red, green, and blue components and saves the resultant images with the provided names.

### RGBCombineCommand.java:
Extends `AbstractCombineSplitCommand.java` and is designed to combine separate red, green, and blue components into a single RGB image. Through this class, the model is directed to execute the appropriate image processing task as per the user's command.

- **Package**: controller.commands

- **Fields**:
  - `imageName`: Specifies the name of the combined RGB image.
  - `redImageName`: Name of the source image for the red component.
  - `greenImageName`: Name of the source image for the green component.
  - `blueImageName`: Name of the source image for the blue component.

- **Methods**:
  - **Constructor**: `RGBCombineCommand(String ImageName, String redImageName, String greenImageName, String blueImageName, IImageModel model)`: Constructs the command with the provided image names for the RGB channels and a reference to the image model.
  - `processImage()`: Overrides the abstract method from `AbstractCombineSplitCommand.java`. It uses the underlying image model to combine the separate red, green, and blue components into a single RGB image and saves the resultant image with the specified name.

### AdjustLevelsCommand.java:
A concrete command class in the controller.commands package, designed to adjust the intensity levels of an image's shadows, midtones, and highlights. It extends `AbstractSplitCommand` to optionally incorporate a split effect where part of the image remains unaltered.

- **Package**: controller.commands

- **Fields**:
    - `b`: An integer representing the adjustment value for shadows (brightness).
    - `m`: An integer representing the adjustment value for midtones.
    - `w`: An integer representing the adjustment value for highlights (white levels).

- **Methods**:
    - **Constructor**: `AdjustLevelsCommand(int b, int m, int w, String imageName, String destImageName, IImageModel model, Optional<Double> splitPercentage)`: Instantiates the command with specific values for brightness, midtones, and white levels adjustments, along with the source image name, destination image name, image model reference, and an optional percentage for the split effect.
    - `processImage()`: Implements the abstract method from `AbstractSplitCommand`. It instructs the model to perform level adjustments on the specified image, considering the split percentage if provided. Throws an exception if the image processing fails.

### ColorCorrectCommand.java:
Part of the `controller.commands` package, this command class is responsible for adjusting the color balance of an image to produce a more natural or desired appearance. It extends the `AbstractSplitCommand` for optional application of the color correction effect up to a certain percentage of the image, providing a split view functionality.

- **Package**: controller.commands

- **Methods**:
    - **Constructor**: `ColorCorrectCommand(String imageName, String destImageName, IImageModel model, Optional<Double> splitPercentage)`: Initializes a new instance of `ColorCorrectCommand` with the specified image names, the model, and an optional split percentage. The split percentage determines the extent of the image to which the color correction will be applied.
    - `processImage()`: This overridden method directs the model to apply color correction to the source image. If a split percentage is specified, the color correction is applied only up to that point, leaving the rest of the image in its original state. The method may throw an exception if the image processing encounters issues.

### CompressCommand.java:

Part of the `controller.commands` package, this command class handles the compression of an image using the Haar Wavelet Transform. The compression is done based on a specified compression ratio.

- **Package**: controller.commands

- **Fields**:
  - `compressionRatio`: The ratio by which the image is to be compressed.

- **Methods**:
  - **Constructor**: `CompressCommand(int compressionRatio, String imageName, String destImageName, IImageModel model)`
    Constructs a new `CompressCommand` object with the specified compression ratio, image name, destination image name, and image model.
  - `processImage()`: `protected void processImage() throws Exception`
    This overridden method processes the image by compressing it using the model's compression method. The compression ratio provided during instantiation is used.

### HistogramCommand.java:

Part of the `controller.commands` package, this command class generates a histogram for an image within the model. It extends `AbstractTransformCommand` and captures the histogram representation of the pixel intensity distribution in the image.

- **Package**: controller.commands

- **Methods**:
  - **Constructor**: `HistogramCommand(String imageName, String destImageName, IImageModel model)`
    Constructs a new `HistogramCommand` with the specified source and destination image names, as well as a reference to the image model.
  - `processImage()`: `protected void processImage() throws Exception`
    Executes the histogram creation process. Retrieves histogram data from the model for the specified image and utilizes the `HistogramRenderer` to create a visual representation of the histogram. The resulting image is then added to the model under the destination image name.

### New Interfaces and Command classes

### AppState.java:
Located in the `controller.commands` package, `AppState` is an enumeration that represents the various states an image can have within the application. This enum is used to manage and track the current status of an image in the image processing application.

- **Package**: controller.commands

- **States**:
  - `NO_IMAGE_LOADED`: Indicates that no image is currently loaded in the application. This is typically the default state when the application starts or when there is no active image to process.
  - `IMAGE_LOADED_UNMODIFIED`: Represents the state where an image is loaded into the application but has not been modified. It indicates that the current image is in its original state since being loaded.
  - `IMAGE_LOADED_MODIFIED`: Signifies that the loaded image has been modified in some way. This state is used to track changes to the image, such as after applying filters or edits.

The `AppState` enum is pivotal in the application's workflow, allowing the system to appropriately respond to user actions based on the current state of the image. It aids in decision-making processes, such as determining whether to prompt the user to save changes before loading a new image or closing the application.

### ICommandBuilder.java:
Located in the `controller.commands` package, the `ICommandBuilder` interface defines a contract for command builder implementations in the application. It represents a generic interface for executing commands within the system.

- **Package**: controller.commands

- **Method**:
  - `execute()`: This method signature defines the execution behavior for command builders. It returns a boolean value indicating the success or failure of the command execution.

The `ICommandBuilder` interface is crucial in ensuring a consistent approach to executing commands across different implementations, allowing for a high degree of flexibility and scalability within the application's architecture. It abstracts the execution logic, enabling various command builder classes to implement this interface and define their specific execution behaviors.

### CommandPair.java:
Contained within the `controller.commands` package, `CommandPair` is a class used to pair together two types of image processing commands: preview and apply. This class facilitates the handling of operations where an immediate preview of the effect is needed before applying the change permanently.

- **Package**: controller.commands

- **Fields**:
  - `private final ICommand previewCommand`: Command to execute for previewing the image modification.
  - `private final ICommand applyCommand`: Command to execute for applying the modification to the image.

- **Constructor**:
  - `CommandPair(ICommand previewCommand, ICommand applyCommand)`: Initializes a new `CommandPair` with specified preview and apply commands.

- **Methods**:
  - `getPreviewCommand()`: Returns the preview command. Used for obtaining the command that generates a preview of the image modification.
  - `getApplyCommand()`: Returns the apply command. Used for obtaining the command that applies the image modification.
  - `hasPreview()`: Returns `true` if a preview command is present, `false` otherwise. This method checks whether there is a command available for previewing changes.
  - `hasApply()`: Returns `true` if an apply command is present, `false` otherwise. This method checks whether there is a command available for applying changes.

The `CommandPair` class is instrumental in the application's command architecture, allowing a clear separation and management of commands that generate previews and those that apply changes. This structure enhances the application's flexibility, enabling users to preview effects before committing to them, thereby improving the user experience and reducing unintended modifications.

### CommandBuilder.java:
Resides in the `controller.commands` package, the `CommandBuilder` class is responsible for creating command pairs based on action commands. It acts as a central point for creating and orchestrating various image processing commands in the application, maintaining the state of image loading and modification.

- **Package**: controller.commands

- **Fields**:
  - `private final IImageModel model`: The image model instance for processing commands.
  - `private final IGUIView view`: The GUI view instance for interaction.
  - `private AppState currentState`: The current state of the image (loaded, modified, etc.).

- **Constructor**:
  - `CommandBuilder(IImageModel model, IGUIView view)`: Constructs a new `CommandBuilder` with the given model and view.

- **Methods**:
  - `createCommand(String actionCommand)`: Creates and returns a `CommandPair` based on the given action command. This method switches over various command types like load, color manipulation, flip, blur, etc., and invokes appropriate methods to handle these commands.
  - `handleLoad()`: Handles the 'load' command. Checks current state, prompts for file loading, and creates a load command if applicable.
  - `handleColorCommand()`: Handles color manipulation commands. Creates a command pair for preview and apply actions for color changes.
  - `handleFlipCommand()`: Handles flip commands. Creates a command pair for preview and apply actions for flipping the image.
  - `handleFilterCommand()`: Handles filter commands like blur, sharpen, etc. Creates a command pair based on user input for preview percentage.
  - `handleCompression()`: Handles image compression commands. Generates a command pair for preview and apply actions based on compression ratio.
  - `handleAdjustLevels()`: Handles adjusting image levels. Generates a command pair for preview and apply actions based on user-input levels.
  - `handleSave()`: Handles the 'save' command. Creates a save command if a path is provided.
  - `checkImageLoaded()`: Checks if an image is loaded in the model. Returns `true` if loaded, otherwise shows an error and returns `false`.

The `CommandBuilder` class is a key component in the application's command structure, linking user actions to the model's command processing. This class streamlines the creation of command pairs for different operations, ensuring a smooth workflow in image processing tasks.

### Features.java:
Located in the `controller.commands` package, the `Features` interface defines a set of methods representing different image processing features and actions available in the application. 

- **Package**: controller.commands

- **Methods**:
  - `loadImage()`: Triggers the process to load an image from a file.
  - `saveImage()`: Initiates the process to save the current image to a file.
  - `redComponent()`: Applies a filter to extract the red component of the image.
  - `greenComponent()`: Applies a filter to extract the green component of the image.
  - `blueComponent()`: Applies a filter to extract the blue component of the image.
  - `verticalFlip()`: Flips the image vertically.
  - `horizontalFlip()`: Flips the image horizontally.
  - `blur()`: Applies a blur effect to the image.
  - `sharpen()`: Applies a sharpening effect to the image.
  - `luma()`: Applies a luma component extraction to the image.
  - `sepia()`: Applies a sepia tone effect to the image.
  - `compress()`: Compresses the image based on a specified ratio.
  - `colorCorrect()`: Adjusts the color balance of the image.
  - `adjustLevels()`: Alters the brightness and contrast levels of the image.
  - `confirm()`: Confirms the application of an operation on the image.
  - `cancel()`: Cancels the current operation and reverts any changes.

Implementing the `Features` interface allows classes to systematically manage a variety of image processing actions, ensuring that all necessary functionalities are covered and can be invoked appropriately in the application's workflow.

### End of new classes

### HistogramRenderer.java:

This class provides static methods to render histograms from an array of values. It is capable of creating an `Image` of a histogram for display or processing.

- **Package**: controller

- **Methods**:
  - `public static Image createHistogramImage(int[][] histograms)`:
    Generates an `Image` representing the histograms of the red, green, and blue color channels. Each channel's histogram is normalized to the same scale for consistent representation.

  - `private static void drawHistogram(Graphics2D graphics, int[] histogram, Color color)`:
    Draws a histogram for a single color channel onto the given `Graphics2D` context. The histogram is represented as a line graph.

  - `private static void drawGrid(Graphics2D graphics, int width, int height, int gridSize)`:
    Draws a grid on the given `Graphics2D` context to help visualize the histogram. The grid is drawn with a specified size separating the lines.

  - `private static void findMaxFrequency(int[]... histograms)`:
    Finds the maximum frequency among the given histograms.

  - `private static int[] normalizeHistogram(int[] histogram, int maxFrequency)`:
    Normalizes the given histogram array to the specified maximum frequency.

  - `private static Image convertBufferedImageToImage(BufferedImage bufferedImage)`:
    Converts a `BufferedImage` to an `Image` object.


### IController.java:
Defines the contract for a controller in MVC architectural pattern. The interface specifies the core responsibilities of the controller, which include interpreting user inputs and orchestrating interactions between the model and the view components.

- **Package**: controller

- **Methods**:
  - `execute()`: The primary entry point for starting the controller's workflow. This method encapsulates the logic to begin the control sequence and typically handles user inputs to manipulate the model and update the view accordingly.

### ImageController.java:
Implements `IController` for managing user commands and image manipulation operations between the model and view components.

- **Package**: controller

- **Fields**:
  - `model`: The `IImageModel` for performing image manipulations.
  - `view`: The `IView` for user interaction and feedback.

- **Methods**:
  - **Constructor**: `ImageController(IImageModel model, IView view)`: Initializes the controller with the specified image model and view.
  - `execute()`: Listens for and processes user input commands until an "exit" command is issued.
  - `executeCommand(String command)`: Interprets and delegates the user's command to the appropriate `ICommand` implementation and provides execution feedback.
  - `runScript(String filePath)`: Executes a batch of commands from a script file, enabling multiple command processing.

### New class: GUIController

### GUIController.java:
Part of the `controller` package, `GUIController` implements the `IController` and `Features` interfaces. It serves as the central controller in the application's GUI mode, coordinating between the view and the model.

- **Package**: controller

- **Constructor**:
  - `GUIController(IImageModel model, IGUIView view)`: Constructs a new `GUIController` with the specified image model and GUI view. It initializes the `CommandBuilder` to handle command creation based on user actions.

- **Methods**:
  - `execute()`: Initializes the GUI mode by setting up the default image names and adding feature actions to the view.
  - `loadImage()`, `saveImage()`, `redComponent()`, `greenComponent()`, `blueComponent()`, `verticalFlip()`, `horizontalFlip()`, `blur()`, `sharpen()`, `luma()`, `sepia()`, `compress()`, `colorCorrect()`, `adjustLevels()`, `confirm()`, `cancel()`: Each method triggers the corresponding feature action by creating and executing commands through the `CommandBuilder`.

- **Private Methods**:
  - `executeCommand(String actionCommand)`: Executes a command based on the given action command string, handling preview and application of changes to the image.
  - `applyOperation()`: Applies the current operation to the main image and updates the view.
  - `cancelOperation()`: Cancels the current operation, reverting any changes in the preview image.
  - `updateViewWithImage(String imageName)`: Updates the GUI view with the image corresponding to the provided image name.
  - `setHistogram()`: Sets the histogram in the GUI view based on the current image.
  - `convertImageToBufferedImage(Image image)`: Converts an `Image` object to a `BufferedImage` for display in the GUI.

This class orchestrates the interaction between the model and the GUI view, handling user inputs, executing appropriate image processing commands, and updating the view based on the outcomes of these commands.

### IImageFileParser.java:
Defines an interface for image file parsing, including loading from and saving to files.

- **Package**: controller

- **Methods**:
  - `loadImage(String path)`: Takes a file path and returns an `Image` object, throwing an `IOException` if an error occurs.
  - `saveImage(String path, Image image)`: Saves an `Image` object to the given file path, throwing an `IOException` if an error occurs.

### AbstractImageParser.java:
Implements `IImageFileParser` and provides a base for concrete image parser implementations, handling common loading and saving functionalities.

- **Package**: controller

- **Methods**:
  - `loadImage(String path)`: Implements the method from `IImageFileParser`. It reads an image file from the given path and converts it into an `Image` object.
  - `saveImage(String path, Image image)`: Implements the method from `IImageFileParser`. It writes an `Image` object to the specified path as an image file.
  - `getImageFormat()`: Abstract method to be implemented by subclasses, which should return the image format as a `String`.
  - `getImageType()`: Abstract method to be implemented by subclasses, which should return the `BufferedImage` type constant.

  The class uses Java AWT `BufferedImage` and `ImageIO` for image operations. It translates between `BufferedImage` instances and `Image` objects, encapsulating the pixel data as `Pixel` objects. This abstraction allows for polymorphic handling of different image formats when extending this class.

### JPEGParser.java:
Extends the functionality of `AbstractImageParser` specifically for JPEG image files.
This class specializes the general parsing behavior defined in `AbstractImageParser` to cater to the specifics of the JPEG image format. It specifies the format and type of images that are to be loaded or saved, ensuring that operations are performed in accordance with the JPEG standard.

- **Package**: controller

- **Methods**:
  - `getImageFormat()`: Overrides the abstract method from `AbstractImageParser` to return `"jpeg"`, specifying the format for JPEG images.
  - `getImageType()`: Overrides the abstract method from `AbstractImageParser` to return `BufferedImage.TYPE_INT_RGB`, which is the appropriate type for JPEG images without alpha information.

### JPGParser.java:
Provides a concrete implementation of `AbstractImageParser` specifically for JPG image files.
By extending `AbstractImageParser`, this class enables the parsing of JPG images, which is one of the most common image formats used for storing and transmitting photographic images on the internet.

- **Package**: controller

- **Methods**:
  - `getImageFormat()`: This method is overridden to return the string `"jpg"`, denoting the image format specific to JPG files.
  - `getImageType()`: This method is overridden to return `BufferedImage.TYPE_INT_RGB`, the standard type for JPG images that store color information without an alpha channel.

### PNGParser.java:
Implements the `AbstractImageParser` for handling PNG image files specifically.
This class fine-tunes the image parsing processes established in `AbstractImageParser` to accommodate the characteristics of the PNG format, which include support for transparency through an alpha channel.

- **Package**: controller

- **Methods**:
  - `getImageFormat()`: Overrides the abstract method from `AbstractImageParser` and returns `"png"`, identifying the image format as PNG.
  - `getImageType()`: Overrides the abstract method from `AbstractImageParser` to return `BufferedImage.TYPE_INT_ARGB`, which is used for PNG images to handle transparency with an alpha channel.

### PPMParser.java:
Implements `IImageFileParser` to manage PPM (Portable Pixmap) image files. This class specifically handles the reading and writing of the PPM image format, which is a simple, uncompressed and high dynamic range format.

- **Package**: controller

- **Methods**:
  - `loadImage(String path)`: Takes a file path as input and returns an `Image` object after reading a PPM file. It parses the PPM header and pixel data, ensuring it conforms to the P3 specification with a maximum color value of 255.
  - `saveImage(String path, Image image)`: Accepts an `Image` object and a file path, then writes the image data to a file in PPM format. It constructs the PPM header and sequentially writes the RGB values for each pixel.

### IScriptParser.java:
Defines the structure for classes that will parse scripts into executable commands within the application. This interface ensures that any class implementing it will be able to take a string representation of a script and translate it into a series of commands that the application can understand and act upon.

- **Package**: controller

- **Methods**:
  - `parse(String script)`: This method is responsible for analyzing the provided script and breaking it down into individual commands. The commands are returned as a `List<String>`, with each element representing a discrete command extracted from the script.

### ScriptParser.java:
Implements the `IScriptParser` interface, providing functionality to parse script files into executable commands. It reads a file from a given path, excluding any lines that are comments (starting with `#`) or empty, and returns a list of commands for execution.

- **Package**: controller

- **Methods**:
  - `parse(String scriptFilePath)`: Receives the path to a script file and processes its contents. It filters out comments and empty lines, returning a `List<String>` with each command that needs to be executed. The method will throw a `RuntimeException` if it encounters any issues reading the file, encapsulating the underlying `IOException`.

## View:
The `View` represents all the components that define the 'View' in the MVC architecture.

### IView.java:
Defines the View interface in an MVC architecture. This interface outlines the responsibilities of the View component, which includes displaying information and messages to the user and retrieving user input.

- **Package**: view

- **Methods**:
  - `showMessage(String message)`: Takes a string message and is responsible for displaying it to the user. This can be used for regular information, results of operations, or status updates.
  - `getInput()`: Does not take any arguments and returns a string that corresponds to the user's input. This method is used to retrieve commands or data entered by the user.
  - `showError(String errorMessage)`: Accepts a string error message and is responsible for displaying error or exception messages to the user. This helps in communicating issues or invalid operations back to the user.

### ConsoleView.java:
Implements the `IView` interface to create a console-based user interface. This class manages the input and output in a console environment, allowing the user to interact with the application through the command line.

- **Package**: view

- **Fields**:
  - `scanner`: A `Scanner` object that reads input from the console.

- **Methods**:
  - **Constructor**: `ConsoleView()`: Initializes a new `ConsoleView` instance with a `Scanner` to read from the console.
  - `showMessage(String message)`: Overrides the `showMessage` method from `IView`. It prints a message to the standard output (console).
  - `getInput()`: Overrides the `getInput` method from `IView`. It reads a line of text from the user input, trims it, and returns it.
  - `showError(String errorMessage)`: Overrides the `showError` method from `IView`. It prints an error message to the standard error output, prefixed with `"ERROR:"`.

### New Interface: IGUIView

### IGUIView.java:
`IGUIView` extends `IView` interface, residing within the `view` package. It defines a higher-level interface for GUI-based interactions in the application, integrating image processing features and GUI elements.

- **Package**: view

- **Methods**:
  - `addFeatures(Features features)`: Binds specific feature implementations provided by the controller to GUI components, enabling user interactions to trigger image processing actions.
  - `showOperationControls(boolean show)`: Controls the visibility and usability of operational GUI controls based on the provided boolean flag.
  - `promptForSplitPercentage()`: Displays a dialog box prompting the user to enter a split percentage for preview operations. Returns an `Optional<Double>` containing the entered value or empty if not applicable.
  - `loadImage()`: Presents a file chooser dialog for the user to select an image file to load. Returns the file path as a string.
  - `saveImage()`: Opens a file chooser dialog for saving the current image. Returns the chosen file path as a string.
  - `promptForLevels()`: Displays a dialog for the user to enter values for brightness, midtones, and highlights adjustments. Returns an array of integers representing these levels.
  - `promptForCompressionRatio()`: Shows a dialog asking for the compression ratio. Returns the entered double value.
  - `confirmDiscardChanges()`: Displays a confirmation dialog to verify if the user intends to discard unsaved changes. Returns a boolean indicating the user's choice.
  - `setImage(BufferedImage image)`: Updates the main image display in the GUI with the given `BufferedImage`.
  - `setHistogram(BufferedImage histogram)`: Sets and displays the histogram of the current image using a `BufferedImage`.

This interface is crucial for enabling interactive GUI elements in the application, facilitating user inputs for various image processing operations and providing mechanisms to reflect changes in the GUI.

### New class: GUIView

### GUIView.java:
`GUIView` extends `JFrame` and implements the `IGUIView` interface, constituting the main graphical user interface of the image processing application. This class is responsible for displaying images, histograms, and providing interactive controls to the user.

- **Package**: view

- **Fields**:
  - Various `JButton` fields for different image processing functions like load, save, apply filters, etc.
  - `JLabel` for displaying the image and histogram.
  - `JScrollPane` for scrolling through large images.

- **Constructor**:
  - `GUIView()`: Initializes the GUI components and layout.

- **Key Methods**:
  - `initializeUI()`: Sets up the GUI layout with image display and control panels.
  - `createButtons()`: Initializes buttons for various features and adds them to the panel.
  - `addFeatures(Features features)`: Binds action listeners to buttons for executing image processing features.
  - `showOperationControls(boolean show)`: Shows or hides operation-specific buttons based on the state of image processing.
  - `promptForSplitPercentage()`, `loadImage()`, `saveImage()`, `promptForLevels()`, `promptForCompressionRatio()`, `confirmDiscardChanges()`: Various methods for prompting user input or confirmation.
  - `setImage(BufferedImage image)`: Updates the image display area with a new image.
  - `setHistogram(BufferedImage histogram)`: Updates the histogram display area.
  - `showMessage(String message)`, `showError(String errorMessage)`: Display informational or error messages to the user.

This class plays a central role in the user interface, handling user interactions and updating the visual components according to the changes in the image processing model.

## Main Execution:

### Application.java:
Serves as the entry point for the image processing application, implementing the MVC (Model-View-Controller) architecture. This class orchestrates the initialization and coordination of the main components: the model for image manipulation, views for user interaction, and controllers for operation management.

- **Key Methods**:
  - `main(String[] args)`: The static method that starts the application. It sets up the `ImageModel` for image processing and determines the mode of operation based on command-line arguments. Supports three modes:
    - Script mode: `java -jar Program.jar -file path-of-script-file` runs the application using a script file for batch command processing.
    - Text mode: `java -jar Program.jar -text` runs the application in an interactive text mode, allowing the user to enter commands manually.
    - GUI mode: `java -jar Program.jar` launches the application with a graphical user interface, offering a visual and interactive experience.

- **Enhancements**:
  - **Command-Line Argument Parsing**: Enhances flexibility by allowing the application to start in different modes based on provided arguments.
  - **Support for GUI**: Integrates a graphical user interface (`GUIView`) as one of the modes, widening the range of user interaction mechanisms.
  - **Controller Flexibility**: Depending on the mode, the application uses either `ImageController` for text/script modes or `GUIController` for the graphical interface, demonstrating adaptability in the controller's role.
  - **Error Handling for Invalid Arguments**: Implements error messages for incorrect usage, guiding the user towards correct application invocation.

The application's adaptability to different modes of operation (script, text, GUI) makes it versatile for various user preferences and use cases, from automated batch processing to interactive GUI-based manipulation.

### Design Changes and Justifications for introduction of GUI:

#### 1. Introduction of Interfaces: `Features`, `ICommandBuilder`, Classes: `CommandBuilder`, `CommandPair`, Enum: `AppState`

- **Introduction of `Features` Interface**:

  **Justification**:
  - _Unified Command Interface_: Provides a consistent interface for controllers to interact with application features, ensuring uniformity and predictability in feature invocation.
  - _Decoupling_: Separates the implementation of features from their declaration, allowing for flexible changes in feature implementations without impacting controller code.

- **Creation of `CommandBuilder` Class**:

  **Justification**:
  - _Centralized Command Creation_: Centralizes the logic for constructing commands, simplifying command instantiation and reducing code redundancy.
  - _Enhanced Readability and Maintenance_: Encapsulates command creation logic, leading to more readable and maintainable code.

- **Usage of `CommandPair` Class**:

  **Justification**:
  - _Dual Command Handling_: Accommodates both preview and final application commands, facilitating scenarios where an immediate preview is needed before finalizing changes.
  - _Clarity in Command Flow_: Provides clear and structured command execution flow, enhancing code readability and maintainability.

- **Employment of `AppState` Enum**:

  **Justification**:
  - _State Management_: Enumerates distinct application states, aiding in managing behavior based on the current state.
  - _Improved Readability_: Enhances code readability and comprehension through clearly defined and named states.

#### 2. Introduction of `GUIController.java`.

- **Justification**:
  - _GUI-specific Control Logic_: Specializes in controlling the graphical user interface, accommodating GUI-specific functionalities and event handling.
  - _Decoupling of UI and Logic_: Keeps the user interface separate from business logic, adhering to MVC principles and allowing for independent modifications.

#### 3. Introduction of `IGUIView.java` and `GUIView.java`.

- **IGUIView Interface**:

  **Justification**:
  - _Standardized GUI Operations_: Defines a consistent set of operations for GUI implementations, ensuring essential methods are uniformly implemented.
  - _Flexibility in UI Implementation_: Enables different GUI implementations while maintaining a standard interface, offering design versatility.

- **GUIView Class**:

  **Justification**:
  - _Concrete GUI Implementation_: Implements the `IGUIView` interface, encapsulating all graphical elements and interactions.
  - _Separation from Business Logic_: Isolates UI code from core application logic, simplifying system management and enabling independent UI changes.
  - _Enhanced User Experience_: Provides an interactive and intuitive graphical interface, potentially increasing usability and accessibility.

# Test Folder

This folder contains the tests for the exhaustive testing of MVC architecture from src folder.

### MockModel.java:
A mock implementation of the `IImageModel` interface for testing purposes. Instead of performing actual image processing, this class logs method calls to verify the interaction between components in the MVC architecture without the computational overhead of image processing.

- **Package**: model

- **Fields**:
  - `log`: A `StringBuilder` instance that accumulates a log of operations called on the mock model.
  - `dummyImage`: An `Image` object that serves as a placeholder image to be returned by methods that would typically return processed images.

- **Methods**:
  - **Constructor**: `MockModel()`: Initializes a new `MockModel` with a log and a dummy image consisting of a single red pixel.
  - `addImage(Image image, String imagePath)`: Logs the invocation of adding an image to the model.
  - `getImage(String imageName)`: Logs the invocation of retrieving an image and returns the dummy image.
  - `redComponent(String imageName, String destImageName)`: Logs the invocation of extracting the red color component from an image.
  - `greenComponent(String imageName, String destImageName)`: Logs the invocation of extracting the green color component from an image.
  - `blueComponent(String imageName, String destImageName)`: Logs the invocation of extracting the blue color component from an image.
  - `valueComponent(String imageName, String destImageName)`: Logs the invocation of extracting the value component from an image.
  - `lumaComponent(String imageName, String destImageName)`: Logs the invocation of extracting the luma component from an image.
  - `intensityComponent(String imageName, String destImageName)`: Logs the invocation of extracting the intensity component from an image.
  - `horizontalFlip(String imageName, String destImageName)`: Logs the invocation of performing a horizontal flip on an image.
  - `verticalFlip(String imageName, String destImageName)`: Logs the invocation of performing a vertical flip on an image.
  - `brightenCommand(int increment, String imageName, String destImageName)`: Logs the invocation of brightening an image.
  - `blur(String imageName, String destImageName)`: Logs the invocation of applying a blur effect to an image.
  - `sharpen(String imageName, String destImageName)`: Logs the invocation of applying a sharpening effect to an image.
  - `sepia(String imageName, String destImageName)`: Logs the invocation of applying a sepia tone to an image.
  - `rgbSplit(String imageName, String destImageNameRed, String destImageNameGreen, String destImageNameBlue)`: Logs the invocation of splitting the RGB components of an image.
  - `rgbCombine(String redImageName, String greenImageName, String blueImageName, String destImageName)`: Logs the invocation of combining separate red, green, and blue component images into a single image.
  - `getLog()`: Returns the log of operations performed by the mock model as a string.

### ConsoleViewTest.java:
Contains unit tests for the `ConsoleView` class, verifying the functionality of displaying messages, reading input, and showing error messages on the console.

### HaarWaveletTransformTest.java:
Tests the `HaarWaveletTransform` class's functionality for correctly handling the haar transform on the given image for compression.

### ImageControllerMainModelTest.java:
Tests the `ImageController` class with a real `ImageModel`, ensuring it correctly handles commands and interacts with the model and view.

### ImageControllerTest.java:
Tests the `ImageController` with a mock model to verify the correct processing of console input and function calls.

### GUIControllerTest.java:
Tests the `GUIController` with a mock view to verify the correct processing of GUI view prompts and function calls.

### CommandBuilderTest.java:
Tests the `CommandBuilder` to verify the correct creation of appropriate Command classes, based on the action command string, which is sent as argument.

### ImageModelTest.java:
Verifies `ImageModel`'s functionality for creating and managing images, including initialization checks, adding images, and ensuring proper handling of null images.

### ImageTest.java:
Verifies the `Image` class's functionality in handling pixel data, including initialization, pixel retrieval, and dimensions correctness.

### JPEGParserTest.java:
Tests the `JPEGParser` class for its ability to load and save JPEG images, ensuring it properly handles valid files and throws exceptions for invalid scenarios.

### JPGParserTest.java:
Tests the `JPGParser` class for its ability to load and save JPG images, ensuring it properly handles valid files and throws exceptions for invalid scenarios.

### PixelTest.java:
The PixelTest class ensures that a `Pixel` object is correctly instantiated with RGB values, returning appropriate color components, and verifies that RGB values are clamped between 0 and 255.

### PNGParserTest.java:
Tests the `PNGParser` class for its ability to load and save PNG images, ensuring it properly handles valid files and throws exceptions for invalid scenarios.

### PPMParserTest.java:
Tests the `PPMParser` class for its ability to load and save PPM images, ensuring it properly handles valid files and throws exceptions for invalid scenarios.

### ScriptParserTest.java:
The ScriptParserTest class checks the `ScriptParser`'s ability to correctly parse command scripts, dealing with valid scripts, scripts with comments and empty lines, and handling non-existent scripts. It verifies that commands are read correctly and that comments and empty lines are ignored.

## RES Folder
This folder contains all the images and files required for testing, and also the results of testing.

### commands.txt:
This is the script which should be used for testing the image processor functionalities.

### images folder:
This folder contains the image which is used as an input file in `commands.txt`. All the image files generated as a result of execution of `commands.txt` are also stored in this folder.
This folder also contains the images which are expected as part of the submission requirements.

### controllerTest folder:
This folder contains the image which is used as an input file in `ImageControllerMainModelTest.java`. All the image files generated as a result of execution of all tests in `ImageControllerMainModelTest.java` are also stored in this folder.

### ClassDiagram.png:
This image shows the class diagram for this image processing application. The class diagram shows names of classes and interfaces, signature of methods and relationships (inheritance and composition).

# Image Citation:
The image used in this project is sourced from Pixabay(https://pixabay.com/), a platform for high-quality stock photos free from copyright restrictions. The specific images can be accessed here - https://pixabay.com/illustrations/film-negative-photographs-slides-1668918/, https://pixabay.com/photos/parrot-yellow-macaw-bird-perched-3601194/.

## Terms of Usage:
The image is released under the Pixabay License(https://pixabay.com/service/license-summary/) which grants permission to use the content without the need for giving credit to the artist under the following conditions:

- For both commercial and non-commercial use.
- No attribution required.

However, redistributing the image on other stock photo platforms is prohibited.


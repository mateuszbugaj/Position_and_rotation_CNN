# Position and rotation CNN
## 'Official' implementation

## Table of contents
1. [Description](#description)
2. [Implementation details](#impl)
3. [Launch](#launch)

## Description <a name="description"></a>
This is a method and a pipeline for development of convolutional neural network designed to predict position in 3D space of specified object as well as its rotation in one axis. Additionaly model predicts the bounding box. Created as an engineering thesis. \
Project includes:
- [Generator of dataset with GUI](dataset_generator)
<br><br>
![dataset_generator_demo](https://imgur.com/XZ6rDK5.jpg)
- [Python Notebook for trainig and validation of NN using TensorFlow Keras](position_rotation_evaluation.ipynb) 
<br><br>
![loss](https://imgur.com/GUN8R12.jpg)

- [Program which uses trained model and shows resoults on chosen input](predictor)
<br><br>
![predictor](https://imgur.com/MLN60T7.jpg)

- [Original paper](Object_detection_and_position_estimation)

- [Trained model in .h5 file format](predictor)

## Implementation details <a name="impl"></a>
todo

## Launch <a name="launch"></a>
Using `dataset_generator` in Intellij, generate images and save them in `dataset_generator/images/[...]` 
or specify the different path in the notebook. \
Run notebook which will generate images with visualization of the network and `.h5` file containing weights and biases. \
Model will be saved in the predictor folder to use it further. \
Predictor takes input from webcam, or virtual webcam and displays prediction directly on window.


import numpy as np
import cv2
import tensorflow as tf
from tensorflow import keras
from tensorflow.math import add, subtract, multiply, divide, sqrt, atan2
import math

def position_error_x(y_true, y_pred):
    error_sum = 0
    samples = len(y_true)
    for i in range(samples):
        error_sum += abs(y_true[i][0] - y_pred[i][0])
    return (error_sum * 10)/samples

def position_error_y(y_true, y_pred):
    error_sum = 0
    for i in range(len(y_true)):
        error_sum += abs(y_true[i][1] - y_pred[i][1])
    return (error_sum * 10)/len(y_true)

def position_error_z(y_true, y_pred):
    error_sum = 0
    for i in range(len(y_true)):
        error_sum += abs(y_true[i][2] - y_pred[i][2])
    return (error_sum * 10)/len(y_true)

def acc_20(y_true, y_pred):
    count = 0
    for i in range(len(y_true)):
        angle_true = RGBToAngle(y_true[i])
        angle_pred = RGBToAngle(y_pred[i])
        
        if(abs(angle_true - angle_pred) < 20):
            count += 1
    return count / len(y_true)

def acc_15(y_true, y_pred):
    count = 0
    for i in range(len(y_true)):
        angle_true = RGBToAngle(y_true[i])
        angle_pred = RGBToAngle(y_pred[i])
        
        if(abs(angle_true - angle_pred) < 15):
            count += 1
    return count / len(y_true)

def acc_10(y_true, y_pred):
    count = 0
    for i in range(len(y_true)):
        angle_true = RGBToAngle(y_true[i])
        angle_pred = RGBToAngle(y_pred[i])
        
        if(abs(angle_true - angle_pred) < 10):
            count += 1
    return count / len(y_true)

def acc_5(y_true, y_pred):
    count = 0
    for i in range(len(y_true)):
        angle_true = RGBToAngle(y_true[i])
        angle_pred = RGBToAngle(y_pred[i])
        
        if(abs(angle_true - angle_pred) < 5):
            count += 1
    return count / len(y_true)

def IoU(y_true, y_pred):
    mean = 0
    for i in range (len(y_true)):
        x1, y1, w1, h1 = y_true[i]
        x2, y2, w2, h2 = y_pred[i]
        
        w_I = min(x1 + w1, x2 + w2) - max(x1, x2)
        h_I = min(y1 + h1, y2 + h2) - max(y1, y2)
        
        if w_I <= 0 or h_I <= 0:  # no overlap
            continue
        else:
            I = w_I * h_I
            U = w1 * h1 + w2 * h2 - I
            mean += I / U
            
    mean /= len(y_true)
    return mean 

def RGBToAngle(rgb):
    angle = math.atan2(math.sqrt(3) * (rgb[1] - rgb[2]), 2 * rgb[0] - rgb[1] - rgb[2])
    if(angle < 0):
        angle += math.pi*2
    
    return math.degrees(angle)

model = keras.models.load_model(
    'adadeltaModel.h5', 
    custom_objects={
        "position_error_x": position_error_x, 
        "position_error_y": position_error_y,
        "position_error_z": position_error_z,
        "acc_20": acc_20,
        "acc_15": acc_15,
        "acc_10": acc_10,
        "acc_5": acc_5,
        "IoU": IoU})

cap = cv2.VideoCapture(2) # Number depends on local setup

while(True):
    # Capture frame-by-frame
    ret, frame = cap.read()

    # Operations on the frame come here
    heigh = frame.shape[0]
    width = frame.shape[1]
    frame = frame[0: heigh, int(width/2 - heigh/2) : int(width/2 + heigh/2)]
    input = cv2.resize(frame, (150, 150))
    inputBigger = cv2.resize(input, (800, 800))

    # Global standarization
    X = (np.array(input).reshape(1, 150, 150, 3) - np.mean(input)) / np.std(input)

    prediction = model.predict(X)

    positionPrediction = prediction[0][0] * 10
    rotationPrediction = prediction[1][0] * 255
    framePrediction = prediction[2][0] * 100

    # Draw frame
    cv2.rectangle(
        inputBigger, 
        (framePrediction[0], framePrediction[1]), 
        (framePrediction[0] + framePrediction[2], framePrediction[1] + framePrediction[3]),
        (0,255,0),
        3)

    # Display rotation
    cv2.putText(
        inputBigger, 
        "Rotation: {r:.2f}".format(r = RGBToAngle(rotationPrediction)), 
        (5, 80), 
        cv2.FONT_HERSHEY_SIMPLEX, 
        1, 
        (255, 255, 255), 
        2)

    # Display position
    cv2.putText(
        inputBigger, 
        "Position: [{px:.0f}, {py:.0f}, {pz:.0f}]".format(
            px = positionPrediction[0], 
            py = positionPrediction[1], 
            pz = positionPrediction[2]), 
        (5, 120), 
        cv2.FONT_HERSHEY_SIMPLEX, 
        1, 
        (255, 255, 255), 
        2)

    # Display the resulting frame
    cv2.imshow('Original', frame)
    cv2.imshow('Input', inputBigger)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

# When everything done, release the capture
cap.release()
cv2.destroyAllWindows()

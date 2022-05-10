import pandas as pd
import shutil
import RandomForest as Rf
import datasetprocessor as dp


def configureTestDataset(symptomsList):
    configuredTestDataPath = 'configuredTestData.csv'
    shutil.copyfile('testdata.csv', configuredTestDataPath)
    configuredTestDataset = pd.read_csv(configuredTestDataPath)

    parsedList = []
    for symptom in symptomsList.split(","):
        parsedList.append(symptom)
        configuredTestDataset[symptom] = 'da'

    for line in configuredTestDataset:
        print(line)

    configuredTestDataset.to_csv(configuredTestDataPath)


def predictDisease():
    train_data = pd.read_csv('traindataset.csv')
    test_data = pd.read_csv('configuredTestData.csv')

    train_dataset = dp.DatasetProcessor(train_data)
    diseases = train_dataset.getAttribValues('boala')
    roots = Rf.RandomForest(train_data)
    # symptoms = list(train_dataset.attributes)
    # print("Boala rezultata: " + Rf.Predict(test_data, roots))
    return Rf.Predict(test_data, roots)
import pandas as pd
import math
import random


class DatasetProcessor:
    def __init__(self, ds):
        self.ds = ds
        self.className = ds.columns[-1]
        self.classLabels = set(ds[self.className])
        self.attributes = ds.columns[0:len(ds.columns)-1]
        self.instanceCount = len(self.ds)

        self.labelCount = {}
        self.labelProb = {}

        for label in self.classLabels:
            self.labelCount[label] = len(ds[ds[self.className] == label])
            self.labelProb[label] = self.labelCount[label] / self.instanceCount

    def getAttribValues(self, attribute):
        return list(set(self.ds[attribute]))

    # submultimea care contine doar instantele cu valoarea attribute_value a atributului "attribute"
    def getSubset(self, attribute, attribute_value):
        return self.ds[self.ds[attribute] == attribute_value]

    # clasa majoritara
    def getLabelWithMaxCount(self):
        maxLabel = ''
        maxCount = 0
        for label in self.classLabels:
            if self.labelCount[label] > maxCount:
                maxLabel = label
                maxCount = self.labelCount[label]
        return maxLabel

    # indicele Gini al intregului set de date
    def getGini(self):
        gini = 0
        for label in self.classLabels:
            p = self.labelProb[label]
            gini += p**2
        return 1 - gini

    # o submultime a setului de date, generata aleator
    def generateRandomSubset(self):
        #count = random.randint(int(math.sqrt(self.instanceCount - 1)), self.instanceCount-1)
        count = random.randint(2, self.instanceCount - 1)
        indices = random.sample(range(self.instanceCount), count)
        return self.ds.iloc[indices, :]

    # o submultime a listei atributelor, generata aleator
    def generateRandomAttribList(self):
        #attribCount = random.randint(int(math.sqrt(len(self.attributes) - 1)), len(self.attributes)-1)
        attribCount = random.randint(2, len(self.attributes) - 1)
        return random.sample(list(self.attributes), attribCount)

from datasetprocessor import *
import pandas as panda
import numpy as np

diseases = []

class Node:
    def __init__(self, name='root', branchName='', children=[]):
        self.name = name
        self.branchName = branchName
        self.children = children.copy()


def printNode(node, parentStrLen = 0):
    indent = ' ' * parentStrLen
    nodeStr = ''
    if node.branchName == '': #root node
        nodeStr = node.name
        print(indent, nodeStr)
    else:
        nodeStr = '--' + node.branchName + '--> ' + node.name
        print(indent, nodeStr)

    for child in node.children:
        printNode(child, parentStrLen + len(nodeStr))


def MinGini(dataprocess, attributeList):
    minGini = 10.0
    minAttribute = ''

    for attribute in attributeList:
        # luam toate valorile atributului
        attributeValues = dataprocess.getAttribValues(attribute)
        giniValue = 0.0

        for attributeValue in attributeValues:
            attributeValueSubset = dataprocess.getSubset(attribute, attributeValue)
            instances = len(attributeValueSubset)

            probability = instances / dataprocess.instanceCount

            giniV = 1 - (instances * (1 / instances) ** 2)
            giniValue += probability * giniV

        if giniValue < minGini:
            minGini = giniValue
            minAttribute = attribute

    return minAttribute


def BuildTree(ds, branchName, attributeList):
    # branchName este numele ramurii dintre nodul curent si parintele sau
    # attribList este o lista ce contine numele atributelor 
	    
    node = Node()
    node.branchName = branchName

    data_process = DatasetProcessor(ds)

    # daca toate instantele din ds au aceeasi clasa, atunci
	# node.name = numele acelei clase
	# return node

	# toate instantele din ds au aceeasi clasa => getGini == 0
    # 1 - (n * 1/n)**2 = 0
    if data_process.getGini() == 0:
        node.name = list(ds[data_process.className])[0]
        return node


    # daca lista atributelor este goala, atunci
	# node.name = clasa care apare cel mai frecvent in ds
	# return node
    if len(attributeList) == 0:
        node.name = data_process.getLabelWithMaxCount()
        return node


    # atributul cu indicele Gini minim
    A = MinGini(data_process, attributeList)
    node.name = A

    # valorile posibile ale atributului A
    AttributeValues = data_process.getAttribValues(A)

    for value in AttributeValues:
        # submultimea lui ds care contine doar instantele cu valoarea value a atributului A
        subset = data_process.getSubset(A, value)

        # daca submultimea este goala
        if len(subset) == 0:
            # un nou nod cu numele dat de clasa care apare cel mai frecvent in ds
            node.children.append(data_process.getLabelWithMaxCount())
        else:
            # o noua lista ce contine atributele din attribList, mai putin atributul A
            newAttribList = []
            for attribute in attributeList:
                if attribute != A:
                    newAttribList.append(attribute)

            # se apeleaza recursiv functia pentru generarea nodului descendent
            node.children.append(BuildTree(subset, value, newAttribList))

    return node


def RandomForest(trainingDataset):
    roots = []
    training_set = DatasetProcessor(trainingDataset)
    depth = 15

    for i in range(depth):
        print('Tree No ', i + 1)
        print('')
        random_subset = training_set.generateRandomSubset()
        random_symptoms = training_set.generateRandomAttribList()
        root = BuildTree(random_subset, '', random_symptoms)
        printNode(root)
        roots.append(root)
    return roots


def Predict(dataset, roots):
    diseases = []

    for j in range(len(roots)):
        aux = None
        node_copy = roots[j]
        maxiter = 0
        while node_copy.children and maxiter <= 500:
            print('maxiter: ' + str(maxiter))
            for child in node_copy.children:
                if child.branchName == dataset[node_copy.name][0]:
                    aux = child
                    pass
            maxiter += 1
            node_copy = aux
        if node_copy:
            diseases.append(node_copy.name)
    frequency = pd.value_counts(np.array(diseases))
    return frequency.index[0]


if __name__ == '__main__':

    train_data = pd.read_csv('traindataset.csv')
    test_data = pd.read_csv('testdata.csv')

    train_dataset = DatasetProcessor(train_data)
    diseases = train_dataset.getAttribValues('boala')
    roots = RandomForest(train_data)
    # symptoms = list(train_dataset.attributes)
    print("Boala rezultata: " + Predict(test_data, roots))



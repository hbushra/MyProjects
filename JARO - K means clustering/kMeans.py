#author: 'hbushra'

import random
import sys
import jaro_winkler_distance as jaro
import constant

class kMeans():
    def __init__(self, k, data):
        self.k = k
        self.data = data
        self.means = []
        self.clusterLabel = [-1 for i in range(len(data))]
        self.__InitializeMeans()

    # Create k random arrays from given list of arrays
    def __InitializeMeans(self):
        indices = random.sample(range(0, len(self.data)), self.k)
        self.means = [self.data[i] for i in indices]
        print(self.means)

    def __Classify(self, item): 
        # Classify item to the mean with minimum distance     
        minimum = sys.maxsize; 
        minjDist = -1
        minPrefix = -1
        index = -1; 
  
        for i in range(self.k):
            # Find distance from item to mean 
            jDist = jaro.get_jaro_distance(item, self.means[i])
            prefix = jaro.get_common_prefix(item, self.means[i])
            dis = jaro.get_jw_dist(jDist, prefix)
  
            if (dis < minimum): 
                minimum = dis
                minjDist = jDist
                minPrefix = prefix
                index = i;
      
        return { 'index': index, 'dist':minimum, 'similarity':minjDist, 'prefix':minPrefix};

    def __UpdateMean(self, result, item):
        # Uses the jaro similarity measure between two strings to create a 
        # centroid string that has double the jaro similarity and half the distance

        if result['similarity'] == 1:
            return
        
        index = result['index']
        mean = self.means[index]
        lenMean = len(mean)
        lenItem = len(item)

        newDist = result['dist'] / 2
        newSimilarity = result['similarity'] * 1.25
        prefixLen = min(min(constant.JARO_WINKLER_PREFIX_SIZE, result['prefix']+2), lenMean//2)
 
        prefixLen += int(min(
                round((newSimilarity - (prefixLen/lenMean)) * lenItem), 
                lenMean-prefixLen))

        remains = max(lenItem-prefixLen, 0)

        return ''.join([
                mean[0:prefixLen], 
                item[prefixLen : (prefixLen+remains)]])

    def calculate_means(self, max_iterations = 1000):
        # Calculate means 
        for e in range(max_iterations): 
  
            # If no change of cluster occurs, halt 
            noChange = True; 
            for i, item in enumerate(self.data):
  
                # Classify item into a cluster and update the 
                # corresponding means.         
                result = self.__Classify(item); 
                #print(item + " " + str(result))

                self.__UpdateMean(result, item);
  
                index = result['index']
                # Item changed cluster 
                if (index != self.clusterLabel[i]): 
                    noChange = False; 
  
                self.clusterLabel[i] = index; 
  
            print('Iteration ' + str(e) + str(self.means))

            # Nothing changed, return 
            if (noChange): 
                break; 
  
        return { 'centroids':self.means, 'labels':self.clusterLabel }






if __name__ == "__main__":
    k = 3
    data = [ "Hello", "world!", "Hiba", "Bushra", "Elephant", "Computer", "Filename", "Mouse", "Summer", "Winter"];
    obj = kMeans(3, data)
    result = obj.calculate_means()
    print(result['centroids'])
    print(result['labels'])


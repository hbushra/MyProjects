from _io import StringIO
import math; #For pow and sqrt
import sys;
from kMeans import kMeans
from random import shuffle, uniform;
import cProfile, pstats
import re
import json

def compute_k_means(k, input_file):
    data = read_data(input_file)
    obj = kMeans(k, data)
    result = obj.calculate_means()
    print(result['centroids'])
    print(result['labels'])

    for i in range(k):
        f = open('cluster{}.txt'.format(i), 'w')
        cluster = [data[idx] for idx, val in enumerate(result['labels']) if (val==i)]
        for item in cluster:
            f.write("%s\n" % item)

def read_data(fileName): 
  
    # Read the file, splitting by lines 
    f = open(fileName, 'r'); 
    lines = f.read().splitlines()
    f.close()
  
    docs_list = list() 
  
    for i in range(1, len(lines)): 
        line = lines[i].split()
        docs_list.append(str(line[0])) 
  
    return docs_list



###_Main_###
def Test():
    compute_k_means(4, 'listfile.txt')


if __name__ == "__main__":
    pr = cProfile.Profile()
    pr.enable()
    Test()
    pr.disable()
    s = StringIO()
    sortby = 'cumulative'
    ps = pstats.Stats(pr, stream=s).sort_stats(sortby)
    ps.print_stats()
    print(s.getvalue())
from __future__ import division
import constant
import math
import cProfile
import profile
import re

def get_jaro_distance(a, b):
    # Register strings length.
    a_len = len(a)
    b_len = len(b)

    # If one string has null length, we return 0. 
    if a_len == 0 or b_len == 0:
        return 0.0

    # Calculate max length range.
    max_range = max(0, max(a_len, b_len) // 2 - 1)

    # Create 2 lists of integers.
    a_matches = [False] * a_len
    b_matches = [False] * b_len

    # Calculate matching characters. 
    matching_characters = 0
 
    for a_index in range(a_len):
        # Calculate window test limits (limit inferior to 0 and superior to b_len).
        min_index = max(a_index - max_range, 0)
        max_index = min(a_index + max_range + 1, b_len)

        if min_index >= max_index:
            # No more common character because we don't have characters in b to test with characters in a.
            break
 
        for b_index in range(min_index, max_index):
            if not b_matches[b_index] and a[a_index] == b[b_index]:
                # Found some new match.
                a_matches[a_index] = True
                b_matches[b_index] = True
                matching_characters += 1
                break

    # If no matching characters, we return 0.
    if matching_characters == 0:
        return 0.0

    # Calculate character transpositions.
    a_position = [0] * matching_characters
    b_position = [0] * matching_characters
 
    position_index = 0
    for a_index in range(a_len):
        if a_matches[a_index]:
            a_position[position_index] = a_index
            position_index += 1
    
    position_index = 0
    for b_index in range(b_len):
        if b_matches[b_index]:
            b_position[position_index] = b_index
            position_index += 1   

    # Counting half-transpositions.
    transpositions = 0 
    for index in range(matching_characters):
        if a[a_position[index]] != b[b_position[index]]:
            transpositions += 1

   # Calculate Jaro Distance.
    return (
        constant.JARO_WEIGHT_STRING_A * (matching_characters / a_len) +
        constant.JARO_WEIGHT_STRING_B * (matching_characters / b_len) +
        constant.JARO_WEIGHT_TRANSPOSITIONS * (matching_characters - transpositions / 2) / matching_characters)


def get_common_prefix(s1, s2):
    # Calculate common string prefix with default prefix size = 4.
    common_prefix = 0

    maxPrefix = min(min(len(s1), len(s2)), constant.JARO_WINKLER_PREFIX_SIZE)

    for i, c in enumerate(s1):
        if c != s2[i]:
            common_prefix = i
            break

    return common_prefix



def get_jaro_winkler_distance(s1, s2):
    # Calculate Jaro distance.
    distance = get_jaro_distance(s1, s2)
    #print(float(distance))

    # if distance is above "boost threshold" bt = 0.7, then add prefix
    # bonus lp(1 - dj) to distance, otherwise return distance.
    if (distance > constant.JARO_WINKLER_BOOST_THRESHOLD):

        # Calculate common string prefix with default prefix size = 4.
        common_prefix = get_common_prefix(s1,s2)

        # Calculate Jaro-Winkler distance with default scaling factor of 0.1
        distance += constant.JARO_WINKLER_SCALING_FACTOR * common_prefix * (1 - distance);

    return 1 - distance
        

def get_jw_dist(jdist, prefix):
    distance = jdist

    # if distance is above "boost threshold" bt = 0.7, then add prefix
    # bonus lp(1 - dj) to distance, otherwise return distance.
    if (distance > constant.JARO_WINKLER_BOOST_THRESHOLD):

        # Calculate Jaro-Winkler distance with default scaling factor of 0.1
        distance += constant.JARO_WINKLER_SCALING_FACTOR * prefix * (1 - distance);

    return 1 - distance

def Test():
    get_jaro_winkler_distance('9086595421814992952_vx54_EXP_INSTALLED_PATCHES_20030101050000_COMPLETE.gz', '8829209112460573252_tsnider-vm04_EXP_INSTALLED_PATCHES_20030101050000_COMPLETE.gz')

def Test1():
    for a,b in [(   'MARTHA',      'MARTHA'),
                (    'DIXON',    'DICKSONX'),
                ('JELLYFISH',  'SMELLYFISH'),
                ('Birthday',    'Bivbnmkj'),
                ('Hello',    'Hello')
                ]:
        print("jaro_winkler_distance(%r, %r) = %.9f" % (a, b, get_jaro_winkler_distance(a, b)));
        #print("get_jw_dist(%r, %r) = %.6f" % (a, b, get_jw_dist(get_jaro_distance(a, b), get_common_prefix(a,b))));


if __name__ == "__main__":

    cProfile.run('re.compile("Test")')
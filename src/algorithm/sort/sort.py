def quickSort(items):
    if items == [] :
        return []
    else:
        pivot = items[0]
        left = [x for x in items if x < pivot]
        right = [x for x in items if x > pivot]
        return quickSort(left)+[pivot]+quickSort(right)
    
def insertSort(items):
    for i in xrange(1, len(items)) :
        for j in range(i):
            if items[i] < items[j]:
                items[i], items[j] = items[j], items[i]

def bInsertSort(items):
    for i in xrange(1, len(items)):
        temp = items[i]
        low = 0
        high = i-1
        while low <= high:
            mid = (low+high)/2
            if items[i] < items[mid]:
                high -= 1
            else: 
                low += 1
        for j in reversed(xrange(high+1,i)):
            items[j+1] = items[j]
        items[high+1] = temp
        
               
            
            
    
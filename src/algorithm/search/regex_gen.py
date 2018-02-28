def regex_gen(lists):
    offset1,prefix = get_prefix(lists);
    offset2,suffix = get_suffix(lists);
    inpattern = get_inpattern(lists, offset1, offset2)
    return "^" + prefix + inpattern + suffix

def get_prefix(lists):
    i = 0
    while true:
        a = lists[0][i]
        for str in lists[1:] :
            if len(str) == i or str[i] != a:
                if i == 0:
                    return 0, ""
                else:
                    return i-1,str[:i-1]
        i += 1
    
def get_suffix(lists):
    i = -1
    while true:
        a = lists[0][i]
        for str in lists[1:]:
            if len(str) < abs(i) or str[i] != a:
                if i == -1:
                    return 0, ""
                else:
                    return i+1, str[i+1:]
        i -= 1
        
def printset(wset):
    p = "["
    count = wset.count("[ ]")
    wset.sort()
    if count > 0:
        if count < len(wset):
            return "(.*)"
        elif count == len(wset):
            return "[ ]"

    for item in set:
        p += item
    p += "]"
    return p
        
def get_inpattern(lists, offset1, offset2):
    inpattern = []
    i = offset1 + 1
    while true:
        wset = []
        for str in lists:
            if i >= len(str) + offset2:
                inpattern.append("(.*)")
                return inpattern
            elif str[i] not in wset:
                if str[i] == " ":
                    wset.append("[ ]")
                else:
                    wset.append(str[i])
        inpattern.append(printset(wset))
    merge_patterns(inpatterns)
        
def merge_patterns(patterns):
    i=1
    result = ""
    while i<len(patterns):
        count = 1
        while patterns[i] == patterns[i-1]:
            count += 1
            i += 1
        if patterns[i-1] != "(.*)" and count > 1:
            result += patterns[i-1] + "+"
        else:
            result += patterns[i-1]
            i += 1
    return result
        
        
                
                 
            
                
        
    
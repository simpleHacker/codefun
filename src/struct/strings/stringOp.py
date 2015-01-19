def reverseStr(S):
    R = ""
    for i in reversed(xrange(len(S))) :
        R = R + S[i] 
    return R

S = "hello"
print S
R = reverseStr(S)
print R

'''
find the longest palindrome in a string
Approach 1: O(n^2), check from each one as center, with hide padding
Approach 2: O(n), Manacher's ALGORITHM: first convert seq to odd len string with '#' padding
http://www.felix021.com/blog/read.php?2040
'''
def naiveLongestPalindromes(seq):
    """
    Given a sequence seq, returns a list l such that l[2 * i + 1]
    holds the length of the longest palindrome centered at seq[i]
    (which must be odd), l[2 * i] holds the length of the longest
    palindrome centered between seq[i - 1] and seq[i] (which must be
    even), and l[2 * len(seq)] holds the length of the longest
    palindrome centered past the last element of seq (which must be 0,
    as is l[0]).

    The actual palindrome for l[i] is seq[s:(s + l[i])] where s is i
    // 2 - l[i] // 2. (// is integer division.)

    Example:
    naiveLongestPalindrome('ababa') -> [0, 1, 0, 3, 0, 5, 0, 3, 0, 1]
    
    Runs in quadratic time.
    """
    seqLen = len(seq)
    lLen = 2 * seqLen + 1
    l = []

    for i in xrange(lLen):
        # If i is even (i.e., we're on a space), this will produce e
        # == s.  Otherwise, we're on an element and e == s + 1, as a
        # single letter is trivially a palindrome.
        s = i / 2
        e = s + i % 2

        # Loop invariant: seq[s:e] is a palindrome.
        while s > 0 and e < seqLen and seq[s - 1] == seq[e]:
            s -= 1
            e += 1

        l.append(e - s)

    return l

def improveLongestPalindromes(seq):
    p = []
    mx = 0
    id = 0
    for i in range(1..len(seq)):
        p[i] =  min(p[2*id-i], mx-i) if mx > i else 1
# when smaller than, it is totally included in palindrome with id as center        
    if p[2*id-i] > mx-i:    
        while seq[i + p[i]] == seq[i - p[i]] : 
            p[i] += 1
        if i + p[i] > mx :
            mx = i + p[i]
            id = i;
        
'''match a phone number in a text with ruby
def show_­regexp(a,r­e)
    if a =~ re
        "#{$`}<<#{$&}>>#{$'}"
    else
        "no match­"
    end
end
show_regexp('very 607 621 2997 haha 201-2­341195',
/\(?\d\d\d\)?\s*-?\s*\d\d\d\s*-?\s*\d\d\d\d/)

str.scan()

// Grep one:
grep '\(([0-9]\{3\})\|[0-9]\{3\}\)[ -]\?[0-9]\{3\}[ -]\?[0-9]\{4\}' file
'''
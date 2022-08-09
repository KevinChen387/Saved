
key_str = input('Enter your key(integer): ')
key_list = list(key_str)
text1 = input('Enter your text(don\'t enter spaces): ')
text1_list = list(text1)
letters = []

for i in range(len(text1)):
    letter = chr(ord(text1_list[i]) ^ int(key_list[i % len(key_str)]))
    letters.append(letter)
    text2 = ''.join(letters)

print('\nEntered text: %s\n\nKey: %s\n\nText: %s' % (text1, key_str, text2))

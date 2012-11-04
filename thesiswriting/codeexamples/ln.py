import sys

filename = sys.argv[1]
print filename

output = ""
counter = 1
with open(filename) as fh:
	for line in fh:
		outline = str(counter) + ":\t" + line
		output += outline
		counter += 1

outfilename = filename + ".ln.txt"
with open(outfilename, 'w') as fh:
	fh.write(output)

# Mutates a son, due to its probability
#
# @param tmut [Float] mutation rate
# @param sons [Array] sons created from crossover
def mutation(tmut, sons)
  sons.each do |son, prob: rand|
    permut(son.chromossome) if prob <= tmut
  end
end

def permut(ind)
  size = ind.size - 1
  x, y = pick_positons(size)
  ind.insert(y, ind.delete_at(x))
end

def pick_positons(size)
  x = rand(size)
  y = rand(size)
  y = rand(size) while x == y
  [x, y]
end

# Mutates a son, due to its probability
#
# @param tmut [Float] mutation rate
# @param sons [Array] array of sons created from crossover
def mutation(tmut, sons)
  sons.each do |son, prob: rand|
    permut(son.chromossome) if prob <= tmut
  end
end

def permut(ind)
  size = ind.size - 1
  x    = rand(size)
  y    = rand(size)
  y    = rand(size) while x == y
  ind.swap!(x, y)
end

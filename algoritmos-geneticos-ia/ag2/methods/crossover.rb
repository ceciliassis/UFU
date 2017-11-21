# Creates pairs sons from pairs of parents
#
# @param parents [Array] parents selected from the selection @see selection
# @return        [Array] sons generated
def crossover(parents)
  sons = []
  parents.each do |pair|
    s1, s2 = make_sons(*pair)
    sons.push(Individual.new(nil, s1), Individual.new(nil, s2))
  end
  sons
end

def make_sons(p1, p2)
  point  = rand(p1.size - 1)
  s1, s2 = construct_sons(p1.chromossome, p2.chromossome, point)
  cyclic_cross(s1, s2)
end

# Cyclic crossover. Manages the problem of repeated values inside the sons
#
# @param s1 [Array] first son
# @param s2 [Array] second son
# @return   [Array] sons
def cyclic_cross(s1, s2)
  visited = []
  s1.cycle.with_index do |ele, index|
    break       if s1.uniq.size == s1.size
    index %= s1.size
    pos    = s1.rindex(ele)
    next        if pos.nil? || pos == index
    pos = index if visited[pos]
    s1.swap_with_other!(s2, pos)
    visited[pos] = true
  end
  [s1, s2]
end

# Creates two arrays from a single point
#
# @param p1    [Array]   p1's chromossome
# @param p2    [Array]   p2's chromossome
# @param point [Integer] the point of change
# @return      [Arrays]  the sons generated
def construct_sons(p1, p2, point)
  s1 = p2[0, point] + p1[point, p1.size]
  s2 = p1[0, point] + p2[point, p2.size]
  [s1, s2]
end

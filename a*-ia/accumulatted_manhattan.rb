# Calculates the acummulated distances of manhattan
#
# @param actual   [Array]   the actual state
# @param expected [Array]   the expected state
# @return         [Numeric] 0 if actual equals expected, acummulated distances
# otherwise
def accumulatted_manhattan(actual, expected)
  actual = actual.flatten
  return 0 if actual == expected
  actual.inject(0) do |sum, element|
    sum + manhattan(element, expected)
  end
end

# Calculates the manhattan distance between elements
# @param element  [Hash]    the element begin compared
# @param expected [Array]   the expected state
# @return         [Numeric] the manhattan distance between elements
def manhattan(element, expected)
  y = (element[:y] - expected[element[:val]][:y]).abs
  x = (element[:x] - expected[element[:val]][:x]).abs
  (y + x)
end

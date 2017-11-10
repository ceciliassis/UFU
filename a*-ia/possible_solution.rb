# Verifies if there's a possible solution for that state, through the
# number of inversions
#
# @param array [Array]   an array in shuffled order
# @return      [Boolean] true if number of inversions if even
# false, otherwise
def possible_solution?(array)
  sum = 0
  array.each.with_index do |elem, index|
    next  if elem.zero?
    break if index + 1 > array.size
    array[index + 1, array.size].each do |ee|
      next if ee.zero?
      sum += 1 if elem > ee
    end
  end
  sum.even?
end

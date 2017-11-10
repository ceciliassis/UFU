# Feeds the position of the 0 (empty) element @see(#build_matrix)
#
# @param zero [Hash] the hash that will contain the coordinates of the 0
# (empty) element
# @param hash [Hash] the hash containning the coordinates of the 0 (empty)
# element
def found_zero(zero, hash)
  zero[:x] = hash[:x]
  zero[:y] = hash[:y]
end

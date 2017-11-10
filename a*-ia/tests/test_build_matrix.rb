require_relative '../codes/build_matrix'
require 'test/unit'

#
class TestBuildMatrix < Test::Unit::TestCase
  @@array  = [7, 2, 4, 5, 0, 6, 8, 3, 1]
  @@matrix = [[{ val: 7, x: 0, y: 0 }, { val: 2, x: 1, y: 0 }, { val: 4, x: 2, y: 0 }],
              [{ val: 5, x: 0, y: 1 }, { val: 0, x: 1, y: 1 }, { val: 6, x: 2, y: 1 }],
              [{ val: 8, x: 0, y: 2 }, { val: 3, x: 1, y: 2 }, { val: 1, x: 2, y: 2 }]]

  def test_corret_positions_returned
    assert_equal(@@matrix, build_matrix(@@array, {}))
  end

  def test_non_nil_keys_for_zero
    zero = {}
    build_matrix(@@array, zero)
    assert_not_equal(nil, zero[:x])
    assert_not_equal(nil, zero[:y])
  end

  def test_zero_coordinates
    zero = {}
    build_matrix(@@array, zero)
    assert_equal({ x: 1, y: 1 }, zero)
  end

  def test_non_empty_returned
    assert_not_equal([], build_matrix(@@array, {}))
  end
end

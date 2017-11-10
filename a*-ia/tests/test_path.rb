require_relative '../codes/path'
require 'test/unit'

#
class TestPath < Test::Unit::TestCase
  @@array = [7, 2, 4, 5, 0, 6, 8, 3, 1]

  def test_empty_other_path_empty_till_here
    path       = Path.new
    path.state = @@array
    assert_equal([], path.till_here)
  end

  def test_empty_other_path_nil_state
    path = Path.new
    assert_equal(nil, path.state)
  end

  def test_empty_other_path_zero_path_value
    path = Path.new
    assert_equal(0, path.path_value)
  end

  def test_first_state_assigment
    path       = Path.new
    path.state = @@array
    assert_equal(path.state, @@array)
  end

  def test_hash_state_assigment
    path       = Path.new
    path.state = @@array

    other_path = Path.new(path, move: :up, m: @@array)
    assert_equal(@@array, other_path.state)
  end

  def test_non_empty_till_here_path_bigger_than_zero
    path       = Path.new
    path.state = @@array

    other_path = Path.new(path, move: :up, mat: @@array)
    assert_not_equal(0, other_path.till_here.size)
  end

  def test_non_empty_other_path_path_value_bigger_than_zero
    path       = Path.new
    path.state = @@array

    other_path = Path.new(path, move: :up, mat: @@array)
    assert_not_equal(0, other_path.path_value)
  end

  def test_not_chaged_other_path_till_here
    path       = Path.new
    path.state = @@array

    other_path = Path.new(path, move: :up, mat: @@array)
    assert_not_equal(path.till_here.object_id,
                     other_path.till_here.object_id)
    assert_not_equal(path.till_here, other_path.till_here)
  end
end
